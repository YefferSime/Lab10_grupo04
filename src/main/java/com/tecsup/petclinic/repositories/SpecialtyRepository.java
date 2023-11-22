package com.tecsup.petclinic.repositories;
import com.tecsup.petclinic.entities.Specialty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository
        extends CrudRepository<Specialty,Integer> {

    List<Specialty> findByName(String name);

    @Override
    List<Specialty> findAll();

}
