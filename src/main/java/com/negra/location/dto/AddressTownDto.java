package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_ADDRESS_TOWN_INVALID;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_ADDRESS_TOWN;

@Data
public class AddressTownDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_TOWN, message = ERROR_ADDRESS_TOWN_INVALID)
    private final String town;
}
