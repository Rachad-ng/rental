package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_MARK_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_MARk;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingMarkDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_MARk, message = ERROR_MARK_LIBELLE)
    private String libelle;
}
