package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    // Getters and Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
