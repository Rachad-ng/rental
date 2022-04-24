package com.negra.location.mapper;

import com.negra.location.dto.FuelDto;
import com.negra.location.model.Fuel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FuelDtoMapper {

    FuelDto fuelToFuelDto(Fuel fuel);

    List<FuelDto> fuelToFuelDto(Collection<Fuel> fuels);
}
