package com.negra.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OilChange extends Maintenance {

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_OIL_CHANGE_MILEAGE_MAX_INVALID)
    @Column(nullable = false, columnDefinition = "int default 0")
    private int maxMileage;

    public OilChange(int amount, int maxMileage, LocalDate date){
        this.amount = amount;
        this.maxMileage = maxMileage;
        this.date = date.atStartOfDay();
    }

}