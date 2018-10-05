package com.sbk.sbkmvcpet.services;

import com.sbk.sbkmvcpet.model.Pet;

import java.util.Set;

public interface PerService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();

}
