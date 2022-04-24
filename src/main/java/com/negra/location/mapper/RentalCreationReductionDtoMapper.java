package com.negra.location.mapper;

import com.negra.location.dto.RentalCreationReductionDto;
import com.negra.location.model.Reduction;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalCreationReductionDtoMapper {

    RentalCreationReductionDto reductionToRentalCreationReductionDto(Reduction reduction);
    List<RentalCreationReductionDto> reductionToRentalCreationReductionDto(Collection<Reduction> reduction);

}
