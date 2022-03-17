package com.negra.location.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.negra.location.utility.ErrorMessage.*;

@Entity
@Table(name = "frais")
public abstract class Frais implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voiture_id", nullable = false)
    private Voiture voiture;

    @NotNull(message = ERROR_SENDS_DATA)
    @Past(message = ERROR_FRAIS_DATE_EFFET_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime dateEffet;

    @NotNull(message = ERROR_SENDS_DATA)
    @Future(message = ERROR_FRAIS_DATE_ECHEANCE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime dateEcheance;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 0, message = ERROR_FRAIS_MONTANT_INVALID)
    @Column(nullable = false)
    private int montant;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(LocalDateTime dateEffet) {
        this.dateEffet = dateEffet;
    }

    public LocalDateTime getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDateTime dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    // Redefinition des methods equals et hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Frais frais = (Frais) o;
        return id != null && Objects.equals(id, frais.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}