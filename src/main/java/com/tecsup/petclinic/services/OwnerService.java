package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

import java.util.List;

/**
 * 
 * @author Dave - Sime - Diego - Alexander
 *
 */
public interface OwnerService {

	/**
	 * 
	 * @param owner
	 * @return
	 */
	Owner create(Owner owner);

	/**
	 * 
	 * @param owner
	 * @return
	 */
	Owner update(Owner owner);

	/**
	 * 
	 * @param id
	 * @throws OwnerNotFoundException
	 */
	void delete(Integer id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	Owner findById(Integer id) throws OwnerNotFoundException;

	/**
	 * 
	 * @param lastname
	 * @return
	 */
	List<Owner> findByLastName(String lastname);

	/**
	 * 
	 * @param address
	 * @return
	 */
	List<Owner> findByAddress(String address);

	/**
	 * 
	 * @param telephone
	 * @return
	 */
	List<Owner> findByTelephone(String telephone);

	/**
	 *
	 * @return
	 */
	List<Owner> findAll();
}