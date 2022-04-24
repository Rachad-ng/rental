package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_CAR_REGISTRATION_NUMBER;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.Pattern.PATTERN_CAR_REGISTRATION_NUMBER;

@Data
public class AgentCarDto implements Serializable {

    private final Long id;
    private final ModelWithImageAndMarkDto model;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_CAR_REGISTRATION_NUMBER, message = ERROR_CAR_REGISTRATION_NUMBER)
    private final String registrationNumber;
}
