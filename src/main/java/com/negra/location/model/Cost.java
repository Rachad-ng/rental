package com.negra.location.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@Entity
@Table(name = "cost")
public abstract class Cost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_FRAIS_DATE_EFFET_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime startDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_FRAIS_DATE_ECHEANCE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime dueDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_FRAIS_MONTANT_INVALID)
    @Column(nullable = false)
    private int amount;

}