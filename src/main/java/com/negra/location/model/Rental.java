package com.negra.location.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;

@Data
@Entity
@Table(name = "rental")
public class Rental implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "reduction_id", nullable = false)
    private Reduction reduction;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_LOCATION_START_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime startDate;

    @Past(message = ERROR_LOCATION_END_DATE_INVALID)
    @Basic
    private LocalDateTime backDate;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_LOCATION_START_KILOMETRAGE_INVALID)
    @Column(nullable = false)
    private int startMileage;

    @Min(value = 0, message = ERROR_LOCATION_END_KILOMETRAGE_INVALID)
    private int backMileage;
}