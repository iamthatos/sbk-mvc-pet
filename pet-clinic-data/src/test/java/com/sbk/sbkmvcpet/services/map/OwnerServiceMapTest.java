package com.sbk.sbkmvcpet.services.map;

import com.sbk.sbkmvcpet.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    
    OwnerServiceMap ownerServiceMap;
    
    final Long ownerId = 1L;

    final String lastName = "Smith";
    
    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();
        
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        
        Set<Owner> owners = ownerServiceMap.findAll();
        
        assertEquals(0, owners.size());
    }

    @Test
    void delete() {

        Owner owner = ownerServiceMap.findById(ownerId);
        
        ownerServiceMap.delete(owner);

        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(0, owners.size());
    }

    @Test
    void save() {
        
        ownerServiceMap.save(Owner.builder().id(2L).build());
        
        Set<Owner> owners = ownerServiceMap.findAll();
        
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);
        
        assertNotNull(owner);
        
        assertEquals(ownerId, owner.getId());
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("foo");
        
        assertNull(smith);
    }

}