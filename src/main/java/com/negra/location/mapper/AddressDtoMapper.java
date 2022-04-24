package com.negra.location.mapper;

import com.negra.location.dto.AddressDto;
import com.negra.location.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressDtoMapper {

    Address addressDtoToAddress(AddressDto addressDto);

}
