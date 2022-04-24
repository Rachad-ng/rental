package com.negra.location.mapper;

import com.negra.location.dto.TechnicalVisitDueDateDto;
import com.negra.location.model.TechnicalVisit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnicalVisitDueDateDtoMapper {

    TechnicalVisitDueDateDto technicalVisitToTechnicalVisitDueDateDto(TechnicalVisit technicalVisit);

}
