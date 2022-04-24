package com.negra.location.mapper;

import com.negra.location.dto.CarCreationDto;
import com.negra.location.model.Car;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CarCreationDtoMapper {

    public abstract Car carCreationDtoToCar(CarCreationDto carCreationDto);

    @BeforeMapping
    protected void setAvailable(CarCreationDto carCreationDto, @MappingTarget Car car){
        car.setAvailable(true);
    }

}
