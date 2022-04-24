package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_CAR_REGISTRATION_NUMBER;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreationCarDto implements Serializable {

    private RentalCreationModelDto model;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_CAR_REGISTRATION_NUMBER, message = ERROR_CAR_REGISTRATION_NUMBER)
    private String registrationNumber;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_MILEAGE)
    private int mileage;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int pricePerDay;
}
