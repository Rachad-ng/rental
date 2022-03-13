package com.negra.location.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;

@Entity
@Table(name = "reduction")
public class Reduction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "reduction")
    private Set<Location> locations = new HashSet<>();

    @NotNull(message = ERROR_SENDS_DATA)
    @NotBlank(message = ERROR_REDUCTION_DURE_REQUIRED)
    @Pattern(regexp = "^[a-zA-Z]{3,10}$", message = ERROR_REDUCTION_DURE_INVALID)
    @Column(nullable = false)
    private String dure;

    @NotNull(message = ERROR_SENDS_DATA)
    @Range(min = 0, max = 1, message = ERROR_REDUCTION_TAUX_INVALID)
    @Column(nullable = false)
    private double tauxReduction;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDure() {
        return dure;
    }

    public void setDure(String dure) {
        this.dure = dure;
    }

    public double getTauxReduction() {
        return tauxReduction;
    }

    public void setTauxReduction(double tauxReduction) {
        this.tauxReduction = tauxReduction;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    // Gestion des relations bi-directionnels

    public void addLocation(Location location){
        location.setReduction(this);
        this.getLocations().add(location);
    }

    public void removeLocation(Location location){
        this.getLocations().remove(location);
    }
}