package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_DURATION;

@Data
@Entity
@Table(name = "reduction")
public class Reduction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "reduction")
    private Set<Rental> rentalSet = new HashSet<>();

    @NotNull(message = ERROR_SEND_DATA)
    @NotBlank(message = ERROR_REDUCTION_DURATION_REQUIRED)
    @Pattern(regexp = PATTERN_DURATION, message = ERROR_REDUCTION_DURATION_INVALID)
    @Column(nullable = false)
    private String duration;

    @NotNull(message = ERROR_SEND_DATA)
    @Range(min = 0, max = 1, message = ERROR_REDUCTION_RATE_INVALID)
    @Column(nullable = false)
    private double reductionRate;

    // Gestion des relations bi-directionnels

    public void addLocation(Rental rental){
        rental.setReduction(this);
        this.getRentalSet().add(rental);
    }

    public void removeLocation(Rental rental){
        this.getRentalSet().remove(rental);
    }
}