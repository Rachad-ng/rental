package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_COLOR;
import static com.negra.location.utility.Pattern.PATTERN_CAR_REGISTRATION_NUMBER;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarCreationDto implements Serializable {

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
    private boolean autoTransmission;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean sunroof;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean tintedGlass;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean childSeat;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_CAR_REGISTRATION_NUMBER, message = ERROR_CAR_REGISTRATION_NUMBER)
    private String registrationNumber;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_CAR_DATE_CIRCULATION)
    private LocalDate dateCirculation;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_MILEAGE)
    private int mileage;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int pricePerDay;

    @NotNull(message = ERROR_CAR_MODEL)
    @Min(value = 1, message = ERROR_CAR_MODEL)
    private long idMark;

    @NotNull(message = ERROR_CAR_MODEL)
    @Min(value = 1, message = ERROR_CAR_MODEL)
    private long idModel;

    @NotNull(message = ERROR_CAR_FUEL)
    @Min(value = 1, message = ERROR_CAR_FUEL)
    private long idFuel;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CATEGORY_NOT_FOUND)
    private long idCategory;

    private List<MarkWithModelDto> markWithModelDtos = new ArrayList<>();

    private List<ModelDto> modelDtos = new ArrayList<>();

    private List<FuelDto> fuelDtos = new ArrayList<>();

    private List<CategoryDto> categoryDtos = new ArrayList<>();

}
