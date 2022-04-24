package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;

@Data
public class ModelImageDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    private final String image;

}
