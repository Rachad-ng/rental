package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_MARK_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_MARk;

@Data
public class MarkDto implements Serializable {

    private final Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_MARk, message = ERROR_MARK_LIBELLE)
    private final String libelle;

}
