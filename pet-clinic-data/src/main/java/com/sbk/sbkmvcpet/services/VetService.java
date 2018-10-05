package com.sbk.sbkmvcpet.services;

import com.sbk.sbkmvcpet.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();

}
