package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_MODEL_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_MODEL;

@Data
public class ModelAndMarkDto implements Serializable {

    private Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_MODEL, message = ERROR_MODEL_LIBELLE)
    private String libelle;

    private MarqueDto marqueDto;

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setMarqueDto(MarqueDto marqueDto) {
        this.marqueDto = marqueDto;
    }
}