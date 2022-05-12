package com.msilva.petclinic.services.jpa;

import com.msilva.petclinic.model.Owner;
import com.msilva.petclinic.repositories.OwnerRepository;
import com.msilva.petclinic.repositories.PetRepository;
import com.msilva.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerJpaService ownerJpaService;
    Set<Owner> ownerSet;
    Owner singleOwner;
    final String ownerLastName = "Silva";

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        Owner silva = new Owner();
        Owner suarez = new Owner();
        silva.setId(1L);
        suarez.setId(2L);
        silva.setLastName(ownerLastName);
        suarez.setLastName("Suárez");
        ownerSet.add(silva);
        ownerSet.add(suarez);
        singleOwner = silva;
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> owners = ownerJpaService.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(eq(1L))).thenReturn(Optional.of(singleOwner));
        Owner owner = ownerJpaService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(eq(1L))).thenReturn(Optional.empty());
        Owner owner = ownerJpaService.findById(1L);
        assertNull(owner);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(eq(ownerLastName)))
                .thenReturn(singleOwner);
        assertEquals(ownerLastName, ownerJpaService.findByLastName(ownerLastName).getLastName());
    }

    @Test
    void save() {
        when(ownerRepository.save(any(Owner.class))).thenReturn(singleOwner);
        Owner savedOwner = ownerJpaService.save(singleOwner);
        assertNotNull(savedOwner);
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    void delete() {
        ownerRepository.delete(singleOwner);
        verify(ownerRepository, times(1)).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        ownerRepository.deleteById(1L);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}