package com.sbk.sbkmvcpet.services.springdatajpa;

import com.sbk.sbkmvcpet.model.Owner;
import com.sbk.sbkmvcpet.repositories.OwnerRepository;
import com.sbk.sbkmvcpet.repositories.PetRepository;
import com.sbk.sbkmvcpet.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private static final String LAST_NAME = "Seboko";
    
    @Mock
    OwnerRepository ownerRepository;
    
    @Mock
    PetRepository petRepository;
    
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnedOwner;
    
    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
                
        Owner owner = service.findByLastName(LAST_NAME);
        
        assertEquals(returnedOwner.getLastName(), owner.getLastName());
        
    }

    @Test
    void findAll() {

        Set<Owner> owners = new HashSet<>();
        Owner owner1 = Owner.builder().id(1L).lastName(LAST_NAME).build();
        owners.add(owner1);
        
        Owner owner2 = Owner.builder().id(2L).lastName(LAST_NAME).build();
        owners.add(owner2);
        
        when(ownerRepository.findAll()).thenReturn(owners);
        
        assertEquals(2, service.findAll().size());
        
    }

    @Test
    void findById() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));

        Owner seboko = service.findById(1L);
        
        assertEquals(returnedOwner.getId(), seboko.getId());
    }

    @Test
    void save() {

        Owner ownerToSave = Owner.builder().id(1L).build();
        
        when(ownerRepository.save(any())).thenReturn(returnedOwner);
        
        Owner savedOwner = service.save(ownerToSave);
        
        assertNotNull(savedOwner);
        
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        
        service.delete(returnedOwner);
        //default is 1 times
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        
        service.deleteById(1L);
        
        verify(ownerRepository).deleteById(anyLong());
    }

}