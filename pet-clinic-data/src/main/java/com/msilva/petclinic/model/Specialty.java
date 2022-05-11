package com.msilva.petclinic.model;

import javax.persistence.Entity;

@Entity
public class Specialty extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
