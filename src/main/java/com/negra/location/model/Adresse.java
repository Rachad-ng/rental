package com.negra.location.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Entity
@Table(name = "adresse")
public class Adresse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 1, message = ERROR_ADRESSE_NUMBER_INVALID)
    @Column(nullable = false)
    private Integer numero;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_RUE, message = ERROR_ADRESSE_RUE_INVALID)
    @Column(nullable = false)
    private String rue;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_QUARTIER, message = ERROR_ADRESSE_QUARTIER_INVALID)
    @Column(nullable = false)
    private String quartier;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_VILLE, message = ERROR_ADRESSE_VILLE_INVALID)
    @Column(nullable = false)
    private String ville;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_PAYS, message = ERROR_ADRESSE_PAYS_INVALID)
    @Column(nullable = false)
    private String pays;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}