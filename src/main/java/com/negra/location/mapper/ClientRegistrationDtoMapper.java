package com.negra.location.mapper;

import com.negra.location.dto.ClientRegistrationDto;
import com.negra.location.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRegistrationDtoMapper {

    Client clientRegistrationDtoToClient(ClientRegistrationDto clientRegistrationDto);

}
