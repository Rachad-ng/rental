package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_DESCRIPTION;

@Data
@Entity
@Table(name = "maintenance")
public class Maintenance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @NotNull(message = ERROR_SEND_DATA)
    @Past(message = ERROR_MAINTENANCE_DATE_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime date;

    @NotNull(message = ERROR_SEND_DATA)
    @NotBlank(message = ERROR_MAINTENANCE_DESCRIPTION_REQUIRED)
    @Pattern(regexp = PATTERN_DESCRIPTION, message = ERROR_MAINTENANCE_DESCRIPTION_INVALID)
    @Column(nullable = false)
    private String description;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_MAINTENANCE_AMOUNT_INVALID)
    @Column(nullable = false)
    private int amount;
}