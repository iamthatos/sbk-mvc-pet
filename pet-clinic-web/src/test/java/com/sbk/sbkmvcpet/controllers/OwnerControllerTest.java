package com.sbk.sbkmvcpet.controllers;

import com.sbk.sbkmvcpet.model.Owner;
import com.sbk.sbkmvcpet.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    
    @Mock
    OwnerService ownerService;
    
    @InjectMocks
    OwnerController controller;
    
    Set<Owner> owners;
    
    MockMvc mockMvc;
    
    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        
        Owner seboko = Owner.builder().id(1L).lastName("Seboko").build();
        owners.add(seboko);
        
        Owner molokomme = Owner.builder().id(1L).lastName("Molokomme").build();
        owners.add(molokomme);
        
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {

        when(ownerService.findAll()).thenReturn(owners);
        
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));

    }

    @Test
    void findOwners() throws Exception {
        
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));
        
        verifyZeroInteractions(ownerService);
    
    }

    @Test
    void showOwner() throws Exception {

        Owner moeketsi = Owner.builder().id(3L).lastName("Moeketsi").build();
        
        when(ownerService.findById(anyLong())).thenReturn(moeketsi);

        mockMvc.perform(get("/owners/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(3L))));

    }

}