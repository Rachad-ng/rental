package com.negra.location.dto;

import lombok.Data;

@Data
public class ModelWithImageDto {

    private final long id;
    private final String libelle;
    private final String image;
    private final MarkDto markDto;

}
