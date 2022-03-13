package com.negra.location.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_MARQUE_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SENDS_DATA;

@Entity
@Table(name = "marque")
public class Marque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = "^[a-zA-Z ]{3,20}$", message = ERROR_MARQUE_LIBELLE)
    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "marque")
    private Set<Model> models = new HashSet<>();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }


    // Gestion d'une relation bi-directionnel

    public void addModel(Model model){
        model.setMarque(this);
        this.getModels().add(model);
    }

    public void removeModel(Model model){
        this.getModels().remove(model);
    }
}