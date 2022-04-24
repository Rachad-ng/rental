package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_AGENT_RS;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_RS_AGENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingAgenceWithTownDto implements Serializable {

    private AddressTownDto address;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_RS_AGENCE, message = ERROR_AGENT_RS)
    private String rsAgence;
}
