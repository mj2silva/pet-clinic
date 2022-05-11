package com.msilva.petclinic.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;

    public Set<Pet> addPet(Pet pet) {
        if (pets == null) {
            pets = new HashSet<>();
        }
        pets.add(pet);
        pet.setOwner(this);
        return pets;
    }
}
