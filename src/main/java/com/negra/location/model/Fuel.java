package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_CAR_FUEL;
import static com.negra.location.utility.Pattern.PATTERN_FUEL_LIBELLE;

@Data
@Entity
@Table(name = "fuel")
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_FUEL_LIBELLE, message = ERROR_CAR_FUEL)
    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "fuel")
    private Set<Car> carSet = new HashSet<>();

    // Gestion des relations bi-directionnels

    public void addCar(Car car){
        car.setFuel(this);
        this.getCarSet().add(car);
    }
}