package com.negra.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.negra.location.utility.ErrorMessage.*;

@Entity
public class Vidange extends Entretien {

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 0, message = ERROR_VIDANGE_KILOMETRAGE_MAX_INVALID)
    @Column(nullable = false, columnDefinition = "int default 0")
    private int kilometrageMaximal;

    // Getters and setters

    public int getKilometrageMaximal() {
        return kilometrageMaximal;
    }

    public void setKilometrageMaximal(int kilometrageMaximal) {
        this.kilometrageMaximal = kilometrageMaximal;
    }

}