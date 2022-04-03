package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

import static com.negra.location.utility.ErrorMessage.*;

@Data
public class HomeSearchCarDto {

    private List<MarkWithModelDto> markWithModelDtos;
    private List<String> towns;

    @Min(value = 0, message = ERROR_MARK_LIBELLE)
    private int idMark;

    @Min(value = 0,message = ERROR_MODEL_LIBELLE)
    private int idModel;

    private String town;

//    @NotNull(message = ERROR_RESERVATION_START_DATE_REQUIRED)
//    @Future(message = ERROR_RESERVATION_START_DATE_INVALID)
    private LocalDate startDate;

//    @NotNull(message = ERROR_RESERVATION_END_DATE_REQUIRED)
//    @Future(message = ERROR_RESERVATION_END_DATE_INVALID)
    private LocalDate backDate;

}
