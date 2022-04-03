package com.negra.location.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_ADDRESS_NUMBER_INVALID)
    @Column(nullable = false)
    private Integer number;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_RUE, message = ERROR_ADDRESS_STREET_INVALID)
    @Column(nullable = false)
    private String street;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_DISTRICT, message = ERROR_ADDRESS_DISTRICT_INVALID)
    @Column(nullable = false)
    private String district;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_TOWN, message = ERROR_ADDRESS_TOWN_INVALID)
    @Column(nullable = false)
    private String town;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_ADDRESS_COUNTRY, message = ERROR_ADDRESS_COUNTRY_INVALID)
    @Column(nullable = false)
    private String country;
}