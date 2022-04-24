package com.negra.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_DESCRIPTION;

@Data
@NoArgsConstructor
@Entity
public class CarRepair extends Maintenance {

    @NotNull(message = ERROR_SEND_DATA)
    @NotBlank(message = ERROR_MAINTENANCE_COMMENT_REQUIRED)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'none'")
    private String comment;

    public CarRepair(int amount, String comment, LocalDate date){
        this.amount = amount;
        this.comment = comment;
        this.date = date.atStartOfDay();
    }

}