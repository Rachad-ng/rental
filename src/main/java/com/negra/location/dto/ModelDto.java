package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_MODEL_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_MODEL;

@Data
public class ModelDto implements Serializable {

    private final Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_MODEL, message = ERROR_MODEL_LIBELLE)
    private final String libelle;

    public ModelDto(long id, String libelle){
        this.id = id;
        this.libelle = libelle;
    }
}
