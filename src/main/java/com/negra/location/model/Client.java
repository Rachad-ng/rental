package com.negra.location.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_CLIENT_FIDELITY;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;

@Data
@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client")
    private Set<Reservation> reservationSet = new HashSet<>();

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CLIENT_FIDELITY)
    @Column(nullable = false, columnDefinition = "int default -1")
    private Integer loyalty;

    // // Constructeur (Inistialisation de la fidelite)

    public Client(){
        super();
        this.setLoyalty(0);
    }

    // Gestion des relations bi-directionnels

    public void addReservation(Reservation reservation){
        reservation.setClient(this);
        this.getReservationSet().add(reservation);
    }

    public void removeReservation(Reservation reservation){
        this.getReservationSet().remove(reservation);
    }
}