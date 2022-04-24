package com.negra.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelWithImageAndMarkDto {

    private long id;
    private String libelle;
    private String image;
    private MarkDto mark;

}
