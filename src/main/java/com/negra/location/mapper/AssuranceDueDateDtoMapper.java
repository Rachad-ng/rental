package com.negra.location.mapper;

import com.negra.location.dto.AssuranceDueDateDto;
import com.negra.location.model.Assurance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssuranceDueDateDtoMapper {

    AssuranceDueDateDto assuranceToAssuranceDueDateDto(Assurance assurance);

}
