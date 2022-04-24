package com.negra.location.mapper;

import com.negra.location.dto.ClientDto;
import com.negra.location.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientDtoMapper {

    ClientDto userToClientDto(User user);

}
