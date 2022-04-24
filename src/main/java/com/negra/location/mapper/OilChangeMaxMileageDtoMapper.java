package com.negra.location.mapper;

import com.negra.location.dto.OilChangeMaxMileageDto;
import com.negra.location.model.OilChange;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OilChangeMaxMileageDtoMapper {

    OilChangeMaxMileageDto oilChangeToOilChangeMaxMileageDto(OilChange oilChange);

}
