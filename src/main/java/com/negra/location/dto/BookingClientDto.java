package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_RESERVATION_STATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingClientDto implements Serializable {

    private Long id;
    private ListingDto car;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_BOOKING_START_DATE_INVALID)
    private LocalDate startDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_BOOKING_BACK_DATE_INVALID)
    private LocalDate backDate;

    @NotNull(message = ERROR_BOOKING_STATE_REQUIRED)
    @Pattern(regexp = PATTERN_RESERVATION_STATE, message = ERROR_BOOKING_STATE_INVALID)
    private String state;

}
