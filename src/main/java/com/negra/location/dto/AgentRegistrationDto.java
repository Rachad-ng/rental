package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Data
public class AgentRegistrationDto implements Serializable {

    @NotNull(message = ERROR_SENDS_DATA)
    @NotBlank(message = ERROR_UTILISATEUR_EMAIL_EMPTY)
    @Email(message = ERROR_UTILISATEUR_EMAIL_INVALID)
    @Pattern(regexp = PATTERN_EMAIL, message = ERROR_UTILISATEUR_EMAIL_INVALID)
    private String email;

    @NotNull(message = ERROR_SENDS_DATA)
    @Size(min = 3, message = ERROR_UTILISATEUR_PASSWORD_INVALID)
    private String password;

    @NotNull(message = ERROR_SENDS_DATA)
    @Size(min = 3, message = ERROR_UTILISATEUR_PASSWORD_INVALID)
    private String passwordConfirmed;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_ROLE, message = ERROR_UTILISATEUR_ROLE_INVALID)
    private String role;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_TEL, message = ERROR_UTILISATEUR_TELE_INVALID)
    private String tel;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_NAME, message = ERROR_UTILISATEUR_NOM_INVALID)
    private String nom;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_NAME, message = ERROR_UTILISATEUR_PRENOM_INVALID)
    private String prenom;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_RS_AGENCE, message = ERROR_AGENT_RS)
    private String rsAgence;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 1, message = ERROR_ADRESSE_NUMBER_INVALID)
    private Integer numero;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_RUE, message = ERROR_ADRESSE_RUE_INVALID)
    private String rue;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_QUARTIER, message = ERROR_ADRESSE_QUARTIER_INVALID)
    private String quartier;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_VILLE, message = ERROR_ADRESSE_VILLE_INVALID)
    private String ville;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_PAYS, message = ERROR_ADRESSE_PAYS_INVALID)
    private String pays;

    // Getters

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public String getRole() {
        return role;
    }

    public String getTel() {
        return tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRsAgence() {
        return rsAgence;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getRue() {
        return rue;
    }

    public String getQuartier() {
        return quartier;
    }

    public String getVille() {
        return ville;
    }

    public String getPays() {
        return pays;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRsAgence(String rsAgence) {
        this.rsAgence = rsAgence;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
