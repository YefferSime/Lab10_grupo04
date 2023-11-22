package com.tecsup.petclinic.web;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;


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


public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        List<OwnerTO> ownersTO = ownerMapper.toOwnerTOList(owners);
        return ResponseEntity.ok(ownersTO);
    }

    @GetMapping(value = "/owners/{id}")
    public ResponseEntity<OwnerTO> findOwnerById(@PathVariable Integer id) {
        try {
            Owner owner = ownerService.findById(id);
            OwnerTO ownerTO = ownerMapper.toOwnerTO(owner);
            return ResponseEntity.ok(ownerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/owners")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerTO> createOwner(@RequestBody OwnerTO ownerTO) {
        Owner newOwner = ownerMapper.toOwner(ownerTO);
        OwnerTO newOwnerTO = ownerMapper.toOwnerTO(ownerService.create(newOwner));
        return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);
    }

    @PutMapping(value ="/owners/{id}")
    public ResponseEntity<OwnerTO> updateOwner(@PathVariable Integer id, @RequestBody OwnerTO ownerTO) {
        try {
            Owner existingOwner = ownerService.findById(id);

            // Update the fields of existingOwner using ownerTO

            ownerService.update(existingOwner);

            OwnerTO updatedOwnerTO = ownerMapper.toOwnerTO(existingOwner);

            return ResponseEntity.ok(updatedOwnerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value ="/owners/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Owner deleted with ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}