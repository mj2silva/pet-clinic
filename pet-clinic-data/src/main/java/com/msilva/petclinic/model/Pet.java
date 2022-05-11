package com.msilva.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = {})
public class Pet extends BaseEntity {
    @ManyToOne
    private PetType petType;
    @ManyToOne
    private Owner owner;
    private LocalDate birthDate;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits;

    public Set<Visit> addVisit(Visit visit) {
        if (this.visits == null) this.visits = new HashSet<>();
        this.visits.add(visit);
        visit.setPet(this);
        return this.visits;
    }
}
