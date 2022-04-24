package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreationDto implements Serializable {

    private RentalCreationReductionDto reduction;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_RENTAL_START_DATE_INVALID)
    private LocalDateTime startDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_RENTAL_START_DATE_INVALID)
    private LocalDateTime backDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_RENTAL_START_MILEAGE_INVALID)
    private int startMileage;

    public RentalCreationDto(RentalCreationReductionDto rentalCreationReductionDto){
        this.reduction = rentalCreationReductionDto;
    }

}
