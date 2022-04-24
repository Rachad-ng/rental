package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OilChangeDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_MAINTENANCE_DATE_INVALID)
    private LocalDateTime date;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_MAINTENANCE_AMOUNT_INVALID)
    private int amount;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_OIL_CHANGE_MILEAGE_MAX_INVALID)
    private int maxMileage;
}
