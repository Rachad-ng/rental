package com.negra.location.mapper;

import com.negra.location.dto.CarAndModelWithImageDto;
import com.negra.location.model.Car;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CarAndModelWithImageDtoMapper {

    CarAndModelWithImageDto carToCarAndModelWithImageDto(Car car);
    List<CarAndModelWithImageDto> carToCarAndModelWithImageDto(Collection<Car> car);

}
