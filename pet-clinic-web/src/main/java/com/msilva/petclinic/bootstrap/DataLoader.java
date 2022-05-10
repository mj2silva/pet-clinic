package com.msilva.petclinic.bootstrap;

import com.msilva.petclinic.model.Owner;
import com.msilva.petclinic.model.PetType;
import com.msilva.petclinic.model.Vet;
import com.msilva.petclinic.services.OwnerService;
import com.msilva.petclinic.services.PetTypeService;
import com.msilva.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading initial data:");
        // Loading pet types
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded pet types...");

        // Loading owners
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        owner1.setFirstName("Manuel");
        owner1.setLastName("Silva");
        ownerService.save(owner1);

        owner2.setFirstName("Anthony");
        owner2.setLastName("Stark");
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        // Loading vets
        Vet vet1 = new Vet();
        Vet vet2 = new Vet();

        vet1.setFirstName("Natasha");
        vet1.setLastName("Romanov");
        vetService.save(vet1);

        vet2.setFirstName("Scott");
        vet2.setLastName("Lang");
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
