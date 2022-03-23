package com.negra.location.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Data
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_ADRESSE_NUMBER_INVALID)
    @Column(nullable = false)
    private Integer number;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_RUE, message = ERROR_ADRESSE_RUE_INVALID)
    @Column(nullable = false)
    private String street;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_QUARTIER, message = ERROR_ADRESSE_QUARTIER_INVALID)
    @Column(nullable = false)
    private String district;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_VILLE, message = ERROR_ADRESSE_VILLE_INVALID)
    @Column(nullable = false)
    private String town;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADRESSE_PAYS, message = ERROR_ADRESSE_PAYS_INVALID)
    @Column(nullable = false)
    private String country;
}