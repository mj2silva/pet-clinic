package com.msilva.petclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {
    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        if (specialties == null) {
            specialties = new HashSet<>();
        }
        return specialties;
    }

}
