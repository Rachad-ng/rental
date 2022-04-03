package com.negra.location.model;

import com.negra.location.utility.NotorietyUtility;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_RS_AGENCE;

@Data
@Entity
public class Agent extends User {

    @OneToMany(mappedBy = "agent")
    private Set<Car> carSet = new HashSet<>();

    @OneToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_RS_AGENCE, message = ERROR_AGENT_RS)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'none'")
    private String rsAgence;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'none'")
    private String notoriety;

    // Constructeur (Inistialisation de la date d'inscription)

    public Agent(){
        super();
        this.setNotoriety(NotorietyUtility.BRONZE);
    }

    // Gestion des relations bi-directionnels

    public void addCar(Car car){
        car.setAgent(this);
        this.getCarSet().add(car);
    }

    public void removeCar(Car car){
        this.getCarSet().remove(car);
    }

    public void addAddress(Address address){
        address.setAgent(this);
        this.setAddress(address);
    }

    public void removeAdresse(Address address){
        this.setAddress(null);
    }

}