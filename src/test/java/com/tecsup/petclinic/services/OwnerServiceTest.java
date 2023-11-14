package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService ;
	@Test
	public void testUpdateOwner() {

		String OWNER_LASTNAME = "Jose";
		String OWNER_ADDRESS = "dave.santivanez@gmail.com";
		String OWNER_TELEPHONE  = "903010882";
		String OWNER_FIRSTNAME  = "Raul";
		String OWNER_CITY  = "Lima";

		Owner owner = new Owner(OWNER_FIRSTNAME, OWNER_LASTNAME, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE );

		// ------------ Create ---------------

		log.info(">" + owner);
		Owner owner1Created = this.ownerService.create(owner);
		log.info(">>" + owner1Created);

		// ------------ Update ---------------

		// Prepare data for update
		owner1Created.setFirstName("Simme");
		owner1Created.setLastName("Huaranca");
		owner1Created.setCity("Arequipa");

		// Execute update
		Owner upgradeOwner = this.ownerService.update(owner1Created);
		log.info(">>>>" + upgradeOwner);

		//            EXPECTED        ACTUAL
		assertEquals("Simme", upgradeOwner.getFirstName());
		assertEquals("Huaranca", upgradeOwner.getLastName());
		assertEquals("Arequipa", upgradeOwner.getCity());
	}
}
