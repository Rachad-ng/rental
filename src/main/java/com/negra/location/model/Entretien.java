package com.negra.location.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.negra.location.utility.ErrorMessage.*;

@Entity
@Table(name = "entretien")
public class Entretien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voiture_id", nullable = false)
    private Voiture voiture;

    @NotNull(message = ERROR_SENDS_DATA)
    @Past(message = ERROR_ENTRETIEN_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime date;

    @NotNull(message = ERROR_SENDS_DATA)
    @NotBlank(message = ERROR_ENTRETIENT_DESCRIPTION_REQUIRED)
    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,100}$", message = ERROR_ENTRETIENT_DESCRIPTION_INVALID)
    @Column(nullable = false)
    private String description;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 0, message = ERROR_ENTRETIENT_MONTANT_INVALID)
    @Column(nullable = false)
    private int montant;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    // Redefinition des methods equals et hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Entretien entretien = (Entretien) o;
        return id != null && Objects.equals(id, entretien.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}