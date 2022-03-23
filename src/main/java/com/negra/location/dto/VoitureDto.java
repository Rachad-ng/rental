package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;

@Data
public class VoitureDto implements Serializable {

    private Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_CAR_DATE_MISE_CIRCULATION)
    private LocalDate dateMiseCirculation;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int prixJour;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean boiteAuto;

    private CategorieDto categorieDto;
    private FuelDto fuelDto;
    private ModelAndMarkDto modelAndMarkDto;

    private int anneeCirculation;

    // Getters and setters

    public void setDateMiseCirculation(LocalDate dateMiseCirculation) {
        this.dateMiseCirculation = dateMiseCirculation;
        anneeCirculation = dateMiseCirculation.getYear();
    }

}
