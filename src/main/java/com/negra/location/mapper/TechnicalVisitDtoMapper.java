package com.negra.location.mapper;

import com.negra.location.dto.TechnicalVisitDto;
import com.negra.location.model.TechnicalVisit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalVisitDtoMapper {

    TechnicalVisitDto technicalVisitToTechnicalVisitDto(TechnicalVisit technicalVisit);

}
