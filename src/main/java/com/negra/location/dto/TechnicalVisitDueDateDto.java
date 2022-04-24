package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.ERROR_COST_END_DATE_INVALID;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalVisitDueDateDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_COST_END_DATE_INVALID)
    private LocalDateTime dueDate;
}
