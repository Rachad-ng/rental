package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.ERROR_OIL_CHANGE_MILEAGE_MAX_INVALID;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OilChangeMaxMileageDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_OIL_CHANGE_MILEAGE_MAX_INVALID)
    private int maxMileage;
}
