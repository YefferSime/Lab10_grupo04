package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.SpecialtyTO;

import com.tecsup.petclinic.entities.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)

public interface SpecialtyMapper {

    SpecialtyMapper INSTANCE = Mappers.getMapper(SpecialtyMapper.class);

    Specialty toSpecialty(SpecialtyTO specialtyTO);

    SpecialtyTO toSpecialtyTO(Specialty specialty);

    List<SpecialtyTO> toSpecialtyTOList(List<Specialty> specialtyList);

    List<Specialty> toSpecialtyList(List<SpecialtyTO> specialtyTOList);
}
