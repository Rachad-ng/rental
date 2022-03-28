package com.negra.location.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_COLOR;

@Data
public class ListingDetailsCarDto implements Serializable {

    private Long id;

    private ListingModelDto listingModelDto;

    private AgentDto agentDto;

    private ListingCategoryDto listingCategoryDto;

    private ListingFuelDto listingFuelDto;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CAR_PLACES_NUMBER)
    private int numberOfPlaces;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CAR_DOORS_NUMBER)
    private int numberOfDoors;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean airConditioning;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_COLOR, message = ERROR_CAR_COLOR)
    private String color;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean androidAvailable;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_CAR_DATE_MISE_CIRCULATION)
    private LocalDate dateCirculation;

    private int circulationYear;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int pricePerDay;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean autoTransmission;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean sunroof;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean tintedGlass;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean childSeat;
}
