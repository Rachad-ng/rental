package com.negra.location.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.negra.location.utility.ErrorMessage.*;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voiture_id", nullable = false)
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @NotNull(message = ERROR_SENDS_DATA)
    @Past(message = ERROR_RESERVATION_START_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @NotNull(message = ERROR_SENDS_DATA)
    @Future(message = ERROR_RESERVATION_END_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime dateFin;

    @NotNull(message = ERROR_RESERVATION_STATE_REQUIRED)
    @Pattern(regexp = "^[a-zA-Z ]{5,10}$", message = ERROR_RESERVATION_STATE_INVALID)
    @Column(nullable = false)
    private String etat;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    // Redefinition des methods equals et hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reservation that = (Reservation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    // Gestion des relations bi-directionnels

    public void addClient(Client client){
        client.getReservations().add(this);
        this.setClient(client);
    }

    public void addLocation(Location location){
        location.setReservation(this);
        this.setLocation(location);
    }

    public void removeLocation(Location location){
        this.setLocation(null);
    }
}