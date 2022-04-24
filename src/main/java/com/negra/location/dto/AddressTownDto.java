package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_ADDRESS_TOWN_INVALID;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_ADDRESS_TOWN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressTownDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_TOWN, message = ERROR_ADDRESS_TOWN_INVALID)
    private String town;
}
