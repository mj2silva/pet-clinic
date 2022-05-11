package com.msilva.petclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vet extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vet_specialty",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"
            ))
    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        if (specialties == null) {
            specialties = new HashSet<>();
        }
        return specialties;
    }

}
