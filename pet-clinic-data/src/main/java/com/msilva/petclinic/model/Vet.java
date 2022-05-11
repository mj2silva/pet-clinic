package com.msilva.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Vet extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vet_specialty",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"
            ))
    private Set<Specialty> specialties;

    public Set<Specialty> addSpecialty(Specialty specialty) {
        if (specialties == null) {
            specialties = new HashSet<>();
        }
        specialties.add(specialty);
        return specialties;
    }

}
