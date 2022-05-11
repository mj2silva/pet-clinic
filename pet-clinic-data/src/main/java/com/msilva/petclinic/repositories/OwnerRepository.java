package com.msilva.petclinic.repositories;

import com.msilva.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    public Owner findByLastName(String lastName);
}
