package com.negra.location.mapper;

import com.negra.location.dto.AssuranceDto;
import com.negra.location.model.Assurance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssuranceDtoMapper {

    AssuranceDto assuranceTOAssuranceDto(Assurance assurance);

}
