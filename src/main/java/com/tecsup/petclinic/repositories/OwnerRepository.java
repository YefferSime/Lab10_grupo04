package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author jgomezm
 *
 */
@Repository
public interface OwnerRepository
	extends CrudRepository<Owner, Integer> {

	// Fetch Owner by name
	List<Owner> findByLastName(String lastname)
	// Fetch Owner by address
	List<Owner> findByAddress(String address);

	// Fetch Owner by telephone
	List<Owner> findByTelephone(int telephone);

	@Override
	List<Owner> findAll();

}
