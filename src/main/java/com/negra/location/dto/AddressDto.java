package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_ADDRESS_NUMBER_INVALID)
    private Integer number;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_RUE, message = ERROR_ADDRESS_STREET_INVALID)
    private String street;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_DISTRICT, message = ERROR_ADDRESS_DISTRICT_INVALID)
    private String district;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_TOWN, message = ERROR_ADDRESS_TOWN_INVALID)
    private String town;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_COUNTRY, message = ERROR_ADDRESS_COUNTRY_INVALID)
    private String country;
}
