package com.msilva.petclinic.bootstrap;

import com.msilva.petclinic.model.*;
import com.msilva.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtiesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtiesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().isEmpty()) {
            loadData();
        }
    }

    private void loadData() {
        System.out.println("Loading initial data:");

        // Loading pet types
        PetType dog = new PetType();
        PetType cat = new PetType();

        dog.setName("Dog");
        cat.setName("Cat");

        PetType savedDogPetType = petTypeService.save(dog);
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Pet types READY!");

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
        ducke.setPetType(savedDogPetType);
        ducke.setOwner(odi);
        ducke.setBirthDate(LocalDate.of(2018, Month.DECEMBER, 15));
        odi.getPets().add(ducke);

        logan.setName("Logan Spielberg");
        logan.setPetType(savedCatPetType);
        logan.setOwner(manuel);
        logan.setBirthDate(LocalDate.of(2020, Month.MAY, 20));
        manuel.getPets().add(logan);

        dumE.setName("Dum-E");
        dumE.setPetType(savedDogPetType);
        dumE.setOwner(tony);
        dumE.setBirthDate(LocalDate.of(2008, Month.JULY, 25));
        tony.getPets().add(dumE);

        ownerService.save(manuel);
        ownerService.save(odi);
        ownerService.save(tony);

        System.out.println("Owners and pets READY!");

        // Loading vets

        Specialty radiology = new Specialty();
        Specialty surgery = new Specialty();
        Specialty dentistry = new Specialty();

        radiology.setName("radiology");
        surgery.setName("surgery");
        dentistry.setName("dentistry");

        Specialty savedRadiologySpecialty = specialtyService.save(radiology);
        Specialty savedSurgerySpecialty = specialtyService.save(surgery);
        Specialty savedDentistrySpecialty = specialtyService.save(dentistry);

        Vet natasha = new Vet();
        Vet scott = new Vet();

        natasha.setFirstName("Natasha");
        natasha.setLastName("Romanov");
        natasha.getSpecialties().add(savedRadiologySpecialty);
        natasha.getSpecialties().add(savedDentistrySpecialty);

        scott.setFirstName("Scott");
        scott.setLastName("Lang");
        scott.getSpecialties().add(savedRadiologySpecialty);
        scott.getSpecialties().add(savedSurgerySpecialty);

        vetService.save(natasha);
        vetService.save(scott);

        System.out.println("Vets and specialties READY!");

        Visit loganVisit = new Visit();
        loganVisit.setPet(logan);
        loganVisit.setDate(LocalDate.now());
        loganVisit.setDescription("Logan visit for vaccines against the flu");

        visitService.save(loganVisit);

        System.out.println("Visits READY!");

        System.out.println("Finished bootstrap data initialization, we are ready to proceed!");
        System.out.println("\uD83D\uDE80 \uD83D\uDE80 \uD83D\uDE80");
    }
}
