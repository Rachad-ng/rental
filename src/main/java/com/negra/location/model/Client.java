package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_CLIENT_LOYALTY;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;

@Data
@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client")
    private Set<Booking> bookingSet = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private Set<Visit> visitSet = new HashSet<>();

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CLIENT_LOYALTY)
    @Column(nullable = false, columnDefinition = "int default -1")
    private Integer loyalty;

    // // Constructeur (Inistialisation de la fidelite)

    public Client(){
        super();
        this.setLoyalty(0);
    }

    // Gestion des relations bi-directionnels

    public void addReservation(Booking booking){
        booking.setClient(this);
        this.getBookingSet().add(booking);
    }

    public void removeReservation(Booking booking){
        this.getBookingSet().remove(booking);
    }

    public void addVisit(Visit visit){
        visit.setClient(this);
        this.getVisitSet().add(visit);
    }

    public void removeVisit(Visit visit){
        this.getVisitSet().remove(visit);
    }
}