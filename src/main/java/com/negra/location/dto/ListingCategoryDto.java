package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ListingCategoryDto implements Serializable {

    @NotNull
    private final String libelle;
}
