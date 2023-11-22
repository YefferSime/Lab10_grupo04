package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;

import java.util.List;

public interface SpecialtyService {
    Specialty create(Specialty specialty);

    Specialty update(Specialty specialty);

    void delete(Integer id) throws OwnerNotFoundException, SpecialtyNotFoundException;

    Specialty findById(Integer id) throws OwnerNotFoundException, SpecialtyNotFoundException;

    List<Specialty> findByName(String name);

    List<Specialty> findAll();
}
