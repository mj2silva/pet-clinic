package com.msilva.petclinic.bootstrap;

import com.msilva.petclinic.model.Owner;
import com.msilva.petclinic.model.Vet;
import com.msilva.petclinic.services.OwnerService;
import com.msilva.petclinic.services.VetService;
import com.msilva.petclinic.services.map.OwnerServiceMap;
import com.msilva.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        // Loading owners
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        owner1.setId(1L);
        owner1.setFirstName("Manuel");
        owner1.setLastName("Silva");
        ownerService.save(owner1);

        owner2.setId(2L);
        owner2.setFirstName("Anthony");
        owner2.setLastName("Stark");
        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        // Loading vets
        Vet vet1 = new Vet();
        Vet vet2 = new Vet();

        vet1.setId(1L);
        vet1.setFirstName("Natasha");
        vet1.setLastName("Romanov");
        vetService.save(vet1);

        vet2.setId(2L);
        vet2.setFirstName("Scott");
        vet2.setLastName("Lang");
        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
