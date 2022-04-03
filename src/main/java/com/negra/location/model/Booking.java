package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_RESERVATION_STATE;

@Data
@Entity
@Table(name = "reservation")
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_RESERVATION_START_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime startDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_RESERVATION_END_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime backDate;

    @NotNull(message = ERROR_RESERVATION_STATE_REQUIRED)
    @Pattern(regexp = PATTERN_RESERVATION_STATE, message = ERROR_RESERVATION_STATE_INVALID)
    @Column(nullable = false)
    private String state;

    // Gestion des relations bi-directionnels

    public void addRental(Rental rental){
        rental.setBooking(this);
        this.setRental(rental);
    }

    public void removeRental(){
        this.setRental(null);
    }
}