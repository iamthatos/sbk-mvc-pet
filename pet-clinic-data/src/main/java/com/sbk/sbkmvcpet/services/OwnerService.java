package com.sbk.sbkmvcpet.services;

import com.sbk.sbkmvcpet.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
