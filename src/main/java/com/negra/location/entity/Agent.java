package com.negra.location.entity;

import com.negra.location.utility.ReputationUtility;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_RS_AGENCE;

@Entity
public class Agent extends Utilisateur {

    @OneToMany(mappedBy = "agent")
    private Set<Voiture> voitures = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_RS_AGENCE, message = ERROR_AGENT_RS)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'none'")
    private String rsAgence;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'none'")
    private String reputation;

    // Getters and Setters
    public String getRsAgence() {
        return rsAgence;
    }

    public void setRsAgence(String rsAgence) {
        this.rsAgence = rsAgence;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public Set<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(Set<Voiture> voitures) {
        this.voitures = voitures;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Adresse getAdresse() {
        return adresse;
    }


    // Constructeur (Inistialisation de la date d'inscription)

    public Agent(){
        super();
        this.setReputation(ReputationUtility.BRONZE);
    }


    // Gestion des relations bi-directionnels

    public void addVoiture(Voiture voiture){
        voiture.setAgent(this);
        this.getVoitures().add(voiture);
    }

    public void removeVoiture(Voiture voiture){
        this.getVoitures().remove(voiture);
    }

    public void addAdresse(Adresse adresse){
        adresse.setAgent(this);
        this.setAdresse(adresse);
    }

    public void removeAdresse(Adresse adresse){
        this.setAdresse(null);
    }

}