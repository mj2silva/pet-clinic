package com.msilva.petclinic.bootstrap;

import com.msilva.petclinic.model.Owner;
import com.msilva.petclinic.model.Pet;
import com.msilva.petclinic.model.PetType;
import com.msilva.petclinic.model.Vet;
import com.msilva.petclinic.services.OwnerService;
import com.msilva.petclinic.services.PetTypeService;
import com.msilva.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

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
        PetType cat = new PetType();

        dog.setName("Dog");
        cat.setName("Cat");

        PetType savedDogPetType = petTypeService.save(dog);
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded pet types...");

        // Loading owners and pets
        Owner manuel = new Owner();
        Owner tony = new Owner();
        Owner odi = new Owner();

        manuel.setFirstName("Manuel");
        manuel.setLastName("Silva");
        manuel.setCity("Trujillo");
        manuel.setAddress("Jr. Cavero y Muñoz 220");
        manuel.setTelephone("994188937");

        tony.setFirstName("Anthony");
        tony.setLastName("Stark");
        tony.setCity("New York");
        tony.setAddress("Avengers Tower");
        tony.setTelephone("944874487");

        odi.setFirstName("Odile");
        odi.setLastName("Gutiérrez De La Cruz");
        odi.setCity("Trujillo");
        odi.setAddress("En algún lugar de La Mancha");
        odi.setTelephone("926629384");

        Pet ducke = new Pet();
        Pet logan = new Pet();
        Pet dumE = new Pet();

        ducke.setName("Ducke");
        ducke.setPetType(dog);
        ducke.setOwner(odi);
        ducke.setBirthDate(LocalDate.of(2018, Month.DECEMBER, 15));
        odi.getPets().add(ducke);

        logan.setName("Logan Spielberg");
        logan.setPetType(cat);
        logan.setOwner(manuel);
        logan.setBirthDate(LocalDate.of(2020, Month.MAY, 20));
        manuel.getPets().add(logan);

        dumE.setName("Dum-E");
        dumE.setPetType(dog);
        dumE.setOwner(tony);
        dumE.setBirthDate(LocalDate.of(2008, Month.JULY, 25));
        tony.getPets().add(dumE);

        ownerService.save(manuel);
        ownerService.save(odi);
        ownerService.save(tony);

        System.out.println("Loaded owners and pets...");

        // Loading vets
        Vet vet1 = new Vet();
        Vet vet2 = new Vet();

        vet1.setFirstName("Natasha");
        vet1.setLastName("Romanov");

        vet2.setFirstName("Scott");
        vet2.setLastName("Lang");

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
