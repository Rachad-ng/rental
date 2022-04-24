package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingCreateRentalDto implements Serializable {

    private Long id;
    private RentalCreationCarDto car;
    private RentalCreationDto rental;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_BOOKING_START_DATE_INVALID)
    private LocalDate startDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_BOOKING_BACK_DATE_INVALID)
    private LocalDate backDate;

}
