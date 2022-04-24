package com.negra.location.mapper;

import com.negra.location.dto.AgentCarDto;
import com.negra.location.model.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgentCarDtoMapper {

    AgentCarDto carToAgentCarDto(Car car);

    List<AgentCarDto> carToAgentCarDto(List<Car> cars);

}
