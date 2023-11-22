package com.tecsup.petclinic.web;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.mapper.SpecialtyMapper;
import com.tecsup.petclinic.domain.SpecialtyTO;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.services.SpecialtyService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@Slf4j

public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyMapper specialtyMapper;

    public SpecialtyController(SpecialtyService specialtyService, SpecialtyMapper specialtyMapper) {
        this.specialtyService = specialtyService;
        this.specialtyMapper = specialtyMapper;
    }

    @GetMapping(value = "/specialties")
    public ResponseEntity<List<SpecialtyTO>> findAllSpecialties() {
        List<Specialty> specialties = specialtyService.findAll();
        List<SpecialtyTO> specialtiesTO = specialtyMapper.toSpecialtyTOList(specialties);
        return ResponseEntity.ok(specialtiesTO);
    }

    @GetMapping(value = "/specialties/{id}")
    public ResponseEntity<SpecialtyTO> findSpecialtyById(@PathVariable int id) {
        try {
            Specialty specialty = specialtyService.findById(id);
            SpecialtyTO specialtyTO = specialtyMapper.toSpecialtyTO(specialty);
            return ResponseEntity.ok(specialtyTO);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (OwnerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "specialties")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SpecialtyTO> createSpecialty(@RequestBody SpecialtyTO specialtyTO) {
        Specialty newSpecialty = specialtyMapper.toSpecialty(specialtyTO);
        SpecialtyTO newSpecialtyTO = specialtyMapper.toSpecialtyTO(specialtyService.create(newSpecialty));
        return ResponseEntity.status(HttpStatus.CREATED).body(newSpecialtyTO);
    }

    @PutMapping(value = "/specialties/{id}")
    public ResponseEntity<SpecialtyTO> updateSpecialty(@PathVariable int id, @RequestBody SpecialtyTO specialtyTO) {
        try {
            Specialty existingSpecialty = specialtyService.findById(id);
            specialtyService.update(existingSpecialty);

            SpecialtyTO updatedSpecialtyTO = specialtyMapper.toSpecialtyTO(existingSpecialty);

            return ResponseEntity.ok(updatedSpecialtyTO);
        } catch (SpecialtyNotFoundException | OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/specialties/{id}")
    public ResponseEntity<String> deleteSpecialty(@PathVariable int id) {
        try {
            specialtyService.delete(id);
            return ResponseEntity.ok("Specialty deleted with ID: " + id);
        } catch (SpecialtyNotFoundException | OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}