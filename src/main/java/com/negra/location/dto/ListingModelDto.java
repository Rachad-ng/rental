package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_MODEL_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_MODEL;

@Data
public class ListingModelDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_MODEL, message = ERROR_MODEL_LIBELLE)
    private final String libelle;

    @NotNull(message = ERROR_SEND_DATA)
    private final String image;

    private final ListingMarkDto listingMarkDto;

}
