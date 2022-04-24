package com.negra.location.mapper;

import com.negra.location.dto.BookingAgentDto;
import com.negra.location.model.Booking;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingAgentDtoMapper {

    BookingAgentDto bookingToBookingAgentDto(Booking booking);
    List<BookingAgentDto> bookingToBookingAgentDto(Collection<Booking> bookings);

}
