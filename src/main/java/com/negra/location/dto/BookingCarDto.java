package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_CAR_REGISTRATION_NUMBER;

@Data
public class BookingCarDto implements Serializable {

    private ModelWithMarkDto model;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_CAR_REGISTRATION_NUMBER, message = ERROR_CAR_REGISTRATION_NUMBER)
    private String registrationNumber;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_MILEAGE)
    private int mileage;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int pricePerDay;

    private AssuranceDueDateDto assurance;
    private TechnicalVisitDueDateDto technicalVisit;
    private CarStickerDueDateDto carSticker;
    private OilChangeMaxMileageDto oilChange;
}
