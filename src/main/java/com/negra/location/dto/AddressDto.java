package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Data
public class AddressDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_ADRESSE_NUMBER_INVALID)
    private Integer number;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_RUE, message = ERROR_ADRESSE_RUE_INVALID)
    private String street;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_QUARTIER, message = ERROR_ADRESSE_QUARTIER_INVALID)
    private String district;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_VILLE, message = ERROR_ADRESSE_VILLE_INVALID)
    private String town;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_PAYS, message = ERROR_ADRESSE_PAYS_INVALID)
    private String country;
}
