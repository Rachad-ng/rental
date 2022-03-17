package com.negra.location.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Data
public class ClientRegistrationDto implements Serializable {

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

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
