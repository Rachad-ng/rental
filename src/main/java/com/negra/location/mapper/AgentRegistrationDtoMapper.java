package com.negra.location.mapper;

import com.negra.location.dto.AgentRegistrationDto;
import com.negra.location.model.Agent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentRegistrationDtoMapper {

    Agent AgentRegistrationDtoToAgent(AgentRegistrationDto agentRegistrationDto);

}
