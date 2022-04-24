package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssuranceDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_COST_START_DATE_INVALID)
    private LocalDateTime startDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_COST_END_DATE_INVALID)
    private LocalDateTime dueDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_COST_AMOUNT_INVALID)
    private int amount;
}
