package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_CAR_PRICE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarAndModelWithImageDto implements Serializable {

    private Long id;
    private ModelWithImageAndMarkDto model;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int pricePerDay;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean autoTransmission;
}
