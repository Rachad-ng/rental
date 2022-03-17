package com.negra.location.model;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.negra.location.utility.ErrorMessage.*;

@Entity
@Table(name = "voiture")
public class Voiture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @OneToMany(mappedBy = "voiture")
    private Set<Frais> listFrais = new HashSet<>();

    @OneToMany(mappedBy = "voiture")
    private Set<Entretien> entretiens = new HashSet<>();

    @OneToMany(mappedBy = "voiture")
    private Set<Reservation> reservations = new HashSet<>();

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = "^[a-zA-z]{6,7}$", message = ERROR_VOITURE_CARBURANT)
    @Column(nullable = false)
    private String carburant;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 1, message = ERROR_VOITURE_NOMBRE_PLACES)
    @Column(nullable = false)
    private int nombrePlaces;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 1, message = ERROR_VOITURE_NOMBRE_PORTES)
    @Column(nullable = false)
    private int nombrePortes;

    @NotNull(message = ERROR_SENDS_DATA)
    @Column(nullable = false)
    private boolean climatisation;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = "^[a-zA-Z ]{3,10}$", message = ERROR_VOITURE_COULEUR)
    @Column(nullable = false)
    private String couleur;

    @NotNull(message = ERROR_SENDS_DATA)
    @Column(nullable = false)
    private boolean posteAndroid;

    @NotNull(message = ERROR_SENDS_DATA)
    @Pattern(regexp = "^[1-9][0-9]{0,4}-[A-Z]-[1-9][0-9]?$", message = ERROR_VOITURE_MATRICULE)
    @Column(nullable = false)
    private String matricule;

    @NotNull(message = ERROR_SENDS_DATA)
    @Past(message = ERROR_VOITURE_DATE_MISE_CIRCULATION)
    @Basic
    @Column(nullable = false)
    private LocalDate dateMiseCirculation;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 0, message = ERROR_VOITURE_KILOMETRAGE)
    @Column(nullable = false)
    private int kilometrage;

    @NotNull(message = ERROR_SENDS_DATA)
    @Min(value = 0, message = ERROR_VOITURE_PRIX)
    @Column(nullable = false)
    private int prixJour;

    @NotNull(message = ERROR_SENDS_DATA)
    @Column(nullable = false)
    private boolean disponible;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public int getNombrePortes() {
        return nombrePortes;
    }

    public void setNombrePortes(int nombrePortes) {
        this.nombrePortes = nombrePortes;
    }

    public boolean isClimatisation() {
        return climatisation;
    }

    public void setClimatisation(boolean climatisation) {
        this.climatisation = climatisation;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public boolean isPosteAndroid() {
        return posteAndroid;
    }

    public void setPosteAndroid(boolean posteAndroid) {
        this.posteAndroid = posteAndroid;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateMiseCirculation() {
        return dateMiseCirculation;
    }

    public void setDateMiseCirculation(LocalDate dateMiseCirculation) {
        this.dateMiseCirculation = dateMiseCirculation;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public int getPrixJour() {
        return prixJour;
    }

    public void setPrixJour(int prixJour) {
        this.prixJour = prixJour;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Set<Frais> getListFrais() {
        return listFrais;
    }

    public void setListFrais(Set<Frais> listFrais) {
        this.listFrais = listFrais;
    }

    public Set<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(Set<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    // Redefinition des methods equals et hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Voiture voiture = (Voiture) o;
        return id != null && Objects.equals(id, voiture.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    // Gestion des relations bi-directionnels

    public void addFrais(Frais frais){
        frais.setVoiture(this);
        this.getListFrais().add(frais);
    }

    public void removeFrais(Frais frais){
        this.getListFrais().remove(frais);
    }

    public void addEntretien(Entretien entretien){
        entretien.setVoiture(this);
        this.getEntretiens().add(entretien);
    }

    public void removeEntretien(Entretien entretien){
        this.getEntretiens().remove(entretien);
    }

    public void addReservation(Reservation reservation){
        reservation.setVoiture(this);
        this.getReservations().add(reservation);
    }

    public void removeReservation(Reservation reservation){
        this.getReservations().remove(reservation);
    }
}