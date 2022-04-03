package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.support.ManagedSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String libelle;

    @OneToMany(mappedBy = "category")
    Set<Car> carSet = new HashSet<>();

    /*
    @ManyToMany
    Set<Model> modelSet = new ManagedSet<>();
     */

    // Gestion des relations bi-directionnels

    public void addCar(Car car){
        car.setCategory(this);
        this.getCarSet().add(car);
    }
}