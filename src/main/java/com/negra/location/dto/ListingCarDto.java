package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;

@Data
public class ListingCarDto implements Serializable {

    private Long id;
    private ListingModelDto listingModelDto;
    private ListingCategoryDto listingCategoryDto;
    private ListingFuelDto listingFuelDto;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean autoTransmission;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_CAR_DATE_MISE_CIRCULATION)
    private LocalDate dateCirculation;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int pricePerDay;

    private int circulationYear;
    // Getters and setters

    public void setDateCirculation(LocalDate dateCirculation) {
        this.dateCirculation = dateCirculation;
        circulationYear = dateCirculation.getYear();
    }
}
