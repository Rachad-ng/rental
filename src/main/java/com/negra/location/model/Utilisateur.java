package com.negra.location.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.*;

@Entity
@Table(name = "utilisateur")
public abstract class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = ERROR_SENDS_DATA)
    @NotBlank(message = ERROR_UTILISATEUR_EMAIL_EMPTY)
    @Email(message = ERROR_UTILISATEUR_EMAIL_INVALID)
    @Pattern(regexp = PATTERN_EMAIL, message = ERROR_UTILISATEUR_EMAIL_INVALID)
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = ERROR_SENDS_DATA)
    @Size(min = 3, message =  ERROR_UTILISATEUR_PASSWORD_INVALID)
    @Column(nullable = false)
    private String password;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_ROLE, message = ERROR_UTILISATEUR_ROLE_INVALID)
    @Column(nullable = false)
    private String role;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp= PATTERN_TEL, message= ERROR_UTILISATEUR_TELE_INVALID)
    @Column(nullable = false)
    private String tel;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_NAME, message = ERROR_UTILISATEUR_NOM_INVALID)
    @Column(nullable = false)
    private String nom;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = PATTERN_NAME, message = ERROR_UTILISATEUR_PRENOM_INVALID)
    @Column(nullable = false)
    private String prenom;

    @NotNull(message = ERROR_UTILISATEUR_DATE_INSCRIPTION_REQUIRED)
    @Past(message = ERROR_UTILISATEUR_DATE_INSCRIPTION_INVALID)
    @Basic
    @Column(nullable = false)
    private LocalDateTime dateInscription;

    @Column(nullable = false)
    private Boolean active;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDateTime dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }


    // Constructeur (Inistialisation de la date d'inscription)

    public Utilisateur(){
        this.setActive(true);
        this.setDateInscription( LocalDateTime.now() );
    }
}