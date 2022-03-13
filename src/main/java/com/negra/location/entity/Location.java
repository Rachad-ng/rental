package com.negra.location.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.negra.location.utility.ErrorMessage.*;

@Entity
@Table(name = "location")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "reduction_id", nullable = false)
    private Reduction reduction;

    @NotNull(message = ERROR_SENDS_DATA)
    @Past(message = ERROR_LOCATION_START_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Past(message = ERROR_LOCATION_END_DATE_INVALID)
    @Basic
    private LocalDateTime dateFin;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 0, message = ERROR_LOCATION_START_KILOMETRAGE_INVALID)
    @Column(nullable = false)
    private int kilometrageDebut;

    @Min(value = 0, message = ERROR_LOCATION_END_KILOMETRAGE_INVALID)
    private int kilometrageRetour;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public int getKilometrageDebut() {
        return kilometrageDebut;
    }

    public void setKilometrageDebut(int kilometrageDebut) {
        this.kilometrageDebut = kilometrageDebut;
    }

    public int getKilometrageRetour() {
        return kilometrageRetour;
    }

    public void setKilometrageRetour(int kilometrageRetour) {
        this.kilometrageRetour = kilometrageRetour;
    }

    public Reduction getReduction() {
        return reduction;
    }

    public void setReduction(Reduction reduction) {
        this.reduction = reduction;
    }

    // Generation des methodes equals et hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return kilometrageDebut == location.kilometrageDebut && kilometrageRetour == location.kilometrageRetour && Objects.equals(id, location.id) && Objects.equals(reservation, location.reservation) && Objects.equals(reduction, location.reduction) && Objects.equals(dateDebut, location.dateDebut) && dateFin.equals(location.dateFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservation, reduction, dateDebut, dateFin, kilometrageDebut, kilometrageRetour);
    }
}