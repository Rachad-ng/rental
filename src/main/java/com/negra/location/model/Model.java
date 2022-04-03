package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_MODEL;

@Data
@Entity
@Table(name = "model")
public class Model implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_MODEL, message = ERROR_MODEL_LIBELLE)
    @Column(nullable = false)
    private String libelle;

    @NotNull(message = ERROR_SEND_DATA)
    private String image;

    @NotNull(message = ERROR_MODEL_REFERENCE_PRICE)
    private int referencePrice;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "mark_id", nullable = false)
    private Mark mark;

    @OneToMany(mappedBy = "model")
    private Set<Car> carSet = new HashSet<>();

    /*
    @ManyToMany
    private Set<Category> categorySet = new HashSet<>();
     */

    // Gestion d'une relation bi-directionnel

    public void addCar(Car car){
        car.setModel(this);
        this.getCarSet().add(car);
    }

    public void removeCar(Car car){
        this.getCarSet().remove(car);
    }
}