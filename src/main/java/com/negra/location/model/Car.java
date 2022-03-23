package com.negra.location.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_COLOR;
import static com.negra.location.utility.Pattern.PATTERN_VOITURE_MATRICULE;

@Data
@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @OneToMany(mappedBy = "car")
    private Set<Cost> costSet = new HashSet<>();

    @OneToMany(mappedBy = "car")
    private Set<Maintenance> maintenanceSet = new HashSet<>();

    @OneToMany(mappedBy = "car")
    private Set<Reservation> reservationSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "fuel_id", nullable = false)
    private Fuel fuel;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CAR_PLACES_NUMBER)
    @Column(nullable = false)
    private int numberPlaces;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CAR_DOORS_NUMBER)
    @Column(nullable = false)
    private int numberDoors;

    @NotNull(message = ERROR_SEND_DATA)
    @Column(nullable = false)
    private boolean airConditioning;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_COLOR, message = ERROR_CAR_COLOR)
    @Column(nullable = false)
    private String color;

    @NotNull(message = ERROR_SEND_DATA)
    @Column(nullable = false)
    private boolean androidAvailable;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_VOITURE_MATRICULE, message = ERROR_CAR_MATRICULE)
    @Column(nullable = false)
    private String registrationNumber;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_CAR_DATE_MISE_CIRCULATION)
    @Basic
    @Column(nullable = false)
    private LocalDate dateCirculation;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_KILOMETRAGE)
    @Column(nullable = false)
    private int Mileage;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    @Column(nullable = false)
    private int pricePerDay;

    @NotNull(message = ERROR_SEND_DATA)
    @Column(nullable = false)
    private boolean available;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean autoTransmission;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Gestion des relations bi-directionnels

    public void addCost(Cost cost){
        cost.setCar(this);
        this.getCostSet().add(cost);
    }

    public void removeCost(Cost cost){
        this.getCostSet().remove(cost);
    }

    public void addMaintenance(Maintenance maintenance){
        maintenance.setCar(this);
        this.getMaintenanceSet().add(maintenance);
    }

    public void removeMaintenance(Maintenance maintenance){
        this.getMaintenanceSet().remove(maintenance);
    }

    public void addReservation(Reservation reservation){
        reservation.setCar(this);
        this.getReservationSet().add(reservation);
    }

    public void removeReservation(Reservation reservation){
        this.getReservationSet().remove(reservation);
    }
}