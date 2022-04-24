package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_CAR_FUEL;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_FUEL_LIBELLE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingFuelDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_FUEL_LIBELLE, message = ERROR_CAR_FUEL)
    private String libelle;
}
