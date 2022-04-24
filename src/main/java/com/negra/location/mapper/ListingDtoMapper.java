package com.negra.location.mapper;

import com.negra.location.dto.ListingDto;
import com.negra.location.model.Car;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ListingDtoMapper {

    ListingDto carToListingDto(Car car);

    List<ListingDto> carToListingDto(Collection<Car> cars);

}
