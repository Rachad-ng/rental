package com.negra.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cost")
public abstract class Cost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    protected Car car;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_COST_START_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    protected LocalDateTime startDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Future(message = ERROR_COST_END_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    protected LocalDateTime dueDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_COST_AMOUNT_INVALID)
    @Column(nullable = false)
    protected int amount;

}