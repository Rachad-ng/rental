package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_CAR_PRICE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;

@Data
public class CarAndModelWithImageDto implements Serializable {

    private final Long id;
    private final ModelWithImageDto modelWithImageDto;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private final int pricePerDay;

    @NotNull(message = ERROR_SEND_DATA)
    private final boolean autoTransmission;
}
