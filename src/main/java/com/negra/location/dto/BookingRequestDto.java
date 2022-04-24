package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;

@Data
public class BookingRequestDto {

    @NotNull(message = ERROR_BOOKING_START_DATE_REQUIRED)
    @Future(message = ERROR_BOOKING_START_DATE_INVALID)
    private LocalDate bookingStartDate;

    @NotNull(message = ERROR_BOOKING_BACK_DATE_REQUIRED)
    @Future(message = ERROR_BOOKING_BACK_DATE_INVALID)
    private LocalDate bookingBackDate;

    @NotNull(message = ERROR_DATA_TO_VALID_BOOKING_REQUIRED)
    @Min(value = 1, message = ERROR_DATA_TO_VALID_BOOKING_REQUIRED)
    private Long idCar;

    public BookingRequestDto(){

    }

    public BookingRequestDto(Long idCar){
        this.idCar = idCar;
    }

}
