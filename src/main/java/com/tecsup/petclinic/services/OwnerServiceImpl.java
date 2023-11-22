package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {


	OwnerRepository ownerRepository;

	public OwnerServiceImpl(OwnerRepository ownerRepository) {
		this. ownerRepository = ownerRepository;
	}


	/**
	 * 
	 * @param owner
	 * @return
	 */
	@Override
	public Owner create(Owner owner) {
		return ownerRepository.save(owner);
	}

	/**
	 * 
	 * @param owner
	 * @return
	 */
	@Override
	public Owner update(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public void delete(Integer id) throws OwnerNotFoundException {

	}


	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Owner findById(Integer id) throws OwnerNotFoundException {

		Optional<Owner> owner = ownerRepository.findById(id);

		if ( !owner.isPresent())
			throw new OwnerNotFoundException("Record not found...!");
			
		return owner.get();
	}

	/**
	 * 
	 * @param lastname
	 * @return
	 */
	@Override
	public List<Owner> findByLastName(String lastname) {

		List<Owner> owners = ownerRepository.findByLastName(lastname);

		owners.stream().forEach(owner -> log.info("" + owner));

		return owners;
	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	@Override
	public List<Owner> findByAddress(String address) {

		List<Owner> owners = ownerRepository.findByAddress(address);

		owners.stream().forEach(owner -> log.info("" + owner));

		return owners;
	}

	/**
	 * 
	 * @param telephone
	 * @return
	 */
	@Override
	public List<Owner> findByTelephone(String telephone) {

		List<Owner> owners = ownerRepository.findByTelephone(telephone);

		owners.stream().forEach(owner -> log.info("" + owner));

		return owners;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public List<Owner> findAll() {
		//
		return ownerRepository.findAll();

	}
}