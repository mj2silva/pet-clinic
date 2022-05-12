package com.msilva.petclinic.services.map;

import com.msilva.petclinic.model.Owner;
import com.msilva.petclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    final Long ownerMockId = 1L;
    final String ownerMockLastName = "Silva";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setLastName(ownerMockLastName);
        ownerServiceMap.save(owner);
    }

    @Test
    void saveWithExistingId() {
        Long id = 2L;
        Owner owner = new Owner();
        owner.setId(id);
        Owner savedOwner = ownerServiceMap.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveAndGenerateId() {
        Owner owner = new Owner();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        assertEquals(2, ownerServiceMap.findAll().size());
        assertTrue(savedOwner.getId() > 0);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerMockId);
        assertEquals(ownerMockId, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        Owner notFoundedOwner = ownerServiceMap.findById(ownerMockId + 10);
        assertNull(notFoundedOwner);
    }

    @Test
    void findByLastName() {
        Owner silva = ownerServiceMap.findByLastName(ownerMockLastName);
        assertNotNull(silva);
        assertEquals(ownerMockLastName, silva.getLastName());
        assertEquals(ownerMockId, silva.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner notFoundedOwner = ownerServiceMap.findByLastName("Williams");
        assertNull(notFoundedOwner);
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerMockId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerMockId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }
}