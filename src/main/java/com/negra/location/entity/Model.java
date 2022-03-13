package com.negra.location.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_MODEL_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SENDS_DATA;

@Entity
@Table(name = "model")
public class Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,20}$", message = ERROR_MODEL_LIBELLE)
    @Column(nullable = false)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "marque_id", nullable = false)
    private Marque marque;

    @OneToMany(mappedBy = "model")
    private Set<Voiture> voitures = new HashSet<>();

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

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Set<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(Set<Voiture> voitures) {
        this.voitures = voitures;
    }

    // Redefinition des methods equals et hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Model model = (Model) o;
        return id != null && Objects.equals(id, model.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    // Gestion d'une relation bi-directionnel

    public void addVoiture(Voiture voiture){
        voiture.setModel(this);
        this.getVoitures().add(voiture);
    }

    public void removeVoiture(Voiture voiture){
        this.getVoitures().remove(voiture);
    }
}