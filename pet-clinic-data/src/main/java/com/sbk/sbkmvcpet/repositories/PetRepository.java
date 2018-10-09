package com.sbk.sbkmvcpet.repositories;

import com.sbk.sbkmvcpet.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long>{
}
