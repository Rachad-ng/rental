package com.negra.location.mapper;

import com.negra.location.dto.BookingCreateRentalDto;
import com.negra.location.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring")
public interface BookingCreateRentalDtoMapper {

    @Mappings({
            @Mapping(target = "rental", source = "booking.rental", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, defaultExpression = "java(new RentalCreationDto(new RentalCreationReductionDto()))")
    })
    BookingCreateRentalDto bookingToBookingCreateRentalDto(Booking booking);

}
