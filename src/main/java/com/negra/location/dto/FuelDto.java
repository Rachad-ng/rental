package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_CAR_ENGINE;
import static com.negra.location.utility.Pattern.PATTERN_FUEL_LIBELLE;

@Data
public class FuelDto implements Serializable {

    private Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_FUEL_LIBELLE, message = ERROR_CAR_ENGINE)
    private String libelle;

}
