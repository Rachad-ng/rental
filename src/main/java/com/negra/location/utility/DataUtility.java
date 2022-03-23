package com.negra.location.utility;

import com.negra.location.dto.CarCreationDto;
import com.negra.location.exception.DateConstraintException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.negra.location.utility.ErrorMessage.ERROR_CAR_DATE_MISE_CIRCULATION;
import static com.negra.location.utility.Pattern.PATTERN_DATE;

public class DataUtility {

    public static final int NUMBER_PLACES_MAX = 10;

    public static void dateCreationCarFormatter(CarCreationDto carCreationDto) throws DateConstraintException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        LocalDate localDate = LocalDate.parse(carCreationDto.getDateCirculation(), formatter);

        if(localDate.isAfter(LocalDate.now()))
            throw new DateConstraintException(ERROR_CAR_DATE_MISE_CIRCULATION);

        carCreationDto.setDateMiseCirculation(localDate);
    }

}
