package com.negra.location.dto;

public class ModelDto {

    private long id;
    private String libelle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String model) {
        this.libelle = model;
    }

    public ModelDto(long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
}
