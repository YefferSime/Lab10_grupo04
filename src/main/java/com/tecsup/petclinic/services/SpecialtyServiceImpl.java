package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> existingSpecialty = specialtyRepository.findById(id);
        if (!existingSpecialty.isPresent()) {
            throw new SpecialtyNotFoundException("Specialty not found with ID: " + id);
        }
        specialtyRepository.deleteById(id);
    }

    @Override
    public Specialty findById(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findById(id);
        if (!specialty.isPresent()) {
            throw new SpecialtyNotFoundException("Specialty not found with ID: " + id);
        }
        return specialty.get();
    }

    @Override
    public List<Specialty> findByName(String name) {
        List<Specialty> specialties = specialtyRepository.findByName(name);
        specialties.forEach(s -> log.info("" + s));
        return specialties;
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }
}
