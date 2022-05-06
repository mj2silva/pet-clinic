package com.msilva.petclinic.services;

import com.msilva.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName (String lastName);
}
