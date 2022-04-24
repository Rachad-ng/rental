package com.negra.location.mapper;

import com.negra.location.dto.ListingDetailsDto;
import com.negra.location.model.Car;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ListingDetailsDtoMapper {

    public abstract ListingDetailsDto carToListingDetailsDto(Car car);

    @AfterMapping
    protected void setHiddenTel(@MappingTarget ListingDetailsDto listingDetailsDto){
        listingDetailsDto.getAgent().setHiddenTelNumber(listingDetailsDto.getAgent().getTel().substring(0,6) + "....");
    }

}
