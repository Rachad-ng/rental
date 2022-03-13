package com.negra.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_CLIENT_FIDELITY;
import static com.negra.location.utility.ErrorMessage.ERROR_SENDS_DATA;

@Entity
public class Client extends Utilisateur {

    @OneToMany(mappedBy = "client")
    private Set<Reservation> reservations = new HashSet<>();

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 0, message = ERROR_CLIENT_FIDELITY)
    @Column(nullable = false, columnDefinition = "int default -1")
    private Integer fidelite;

    // Getters and Setters
    public Integer getFidelite() {
        return fidelite;
    }

    public void setFidelite(Integer fidelite) {
        this.fidelite = fidelite;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    // // Constructeur (Inistialisation de la fidelite)

    public Client(){
        super();
        this.setFidelite(0);
    }


    // Gestion des relations bi-directionnels

    public void addReservation(Reservation reservation){
        reservation.setClient(this);
        this.getReservations().add(reservation);
    }

    public void removeReservation(Reservation reservation){
        this.getReservations().remove(reservation);
    }
}