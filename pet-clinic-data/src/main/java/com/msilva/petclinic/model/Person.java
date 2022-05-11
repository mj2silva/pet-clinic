package com.msilva.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
}
