package com.negra.location.mapper;

import com.negra.location.dto.BookingClientDto;
import com.negra.location.model.Booking;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingClientDtoMapper {

    BookingClientDto bookingToBookingClientDto(Booking booking);

    List<BookingClientDto> bookingToBookingClientDto(Collection<Booking> bookings);

}
