package com.tecsup.petclinic.mapper;

<<<<<<< HEAD
import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
=======

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.domain.PetTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy =  NullValueMappingStrategy.RETURN_DEFAULT)
>>>>>>> 2d91609adf88dab8c1c351d2d19d183e3d6b397e
public interface OwnerMapper {

    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);

    Owner toOwner(OwnerTO ownerTO);

    OwnerTO toOwnerTO(Owner owner);

    List<OwnerTO> toOwnerTOList(List<Owner> ownerList);

    List<Owner> toOwnerList(List<OwnerTO> ownerTOList);

<<<<<<< HEAD
}
=======
}
>>>>>>> 2d91609adf88dab8c1c351d2d19d183e3d6b397e
