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

import static com.negra.location.utility.ErrorMessage.ERROR_MODEL_LIBELLE;
import static com.negra.location.utility.ErrorMessage.ERROR_SEND_DATA;
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "mark_id", nullable = false)
    private Mark mark;

    @OneToMany(mappedBy = "model")
    private Set<Car> carSet = new HashSet<>();

    // Gestion d'une relation bi-directionnel

    public void addCar(Car car){
        car.setModel(this);
        this.getCarSet().add(car);
    }

    public void removeCar(Car car){
        this.getCarSet().remove(car);
    }
}