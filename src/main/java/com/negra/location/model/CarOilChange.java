package com.negra.location.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@Entity
public class CarOilChange extends Maintenance {

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_VIDANGE_KILOMETRAGE_MAX_INVALID)
    @Column(nullable = false, columnDefinition = "int default 0")
    private int maxMileage;

}