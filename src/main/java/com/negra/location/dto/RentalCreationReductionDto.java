package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_DURATION;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCreationReductionDto implements Serializable {

    private Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @NotBlank(message = ERROR_REDUCTION_DURATION_REQUIRED)
    @Pattern(regexp = PATTERN_DURATION, message = ERROR_REDUCTION_DURATION_INVALID)
    private String duration;

    @NotNull(message = ERROR_SEND_DATA)
    @Range(min = 0, max = 1, message = ERROR_REDUCTION_RATE_INVALID)
    private double reductionRate;

}
