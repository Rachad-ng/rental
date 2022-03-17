package com.negra.location.utility;

import com.negra.location.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ObjectInitialisation {

    // User Initialisation
    public static void userInitialisation(Utilisateur user){
        user.setNom("Neggaz");
        user.setPrenom("Rachad");
        user.setTel("0707101019");
        user.setPassword("helloWorld");
        user.setActive(true);
    }

    // Admin initialisation
    public static void adminInitialisation(Administrateur admin){
        userInitialisation(admin);
        admin.setEmail("rachad.ng@gmail.com");
        admin.setRole("ROLE_ADMIN");
    }

    // Agent Initialisation
    public static void agentInitialisation(Agent agent){
        userInitialisation(agent);
        agent.setEmail("rachad.neg@gmail.com");
        agent.setRole("ROLE_AGENT");
        agent.setRsAgence("BestCar");
        agent.setReputation(ReputationUtility.BRONZE);
    }

    // Client Initialisation
    public static void clientInitialisation(Client client){
        userInitialisation(client);
        client.setEmail("rachad-ng@gmail.com");
        client.setRole("ROLE_CLIENT");
        client.setFidelite(0);
    }

    // Adresse Initialisation
    public static void adresseInitialisation(Adresse adresse, Agent agent){
        adresse.setNumero(1);
        adresse.setRue("Lekbab");
        adresse.setQuartier("El Massira");
        adresse.setVille("Berkane");
        adresse.setPays("Maroc");
        adresse.setAgent(agent);
    }

    // Marque Initialisation
    public static void marqueInitialisation(Marque marque){
        marque.setLibelle("Mercedes");
    }

    // Model Initialisation
    public static void modelInitialisation(Model model, Marque marque){
        model.setLibelle("C220");
        model.setMarque(marque);
    }

    // Voiture Initialisation
    public static void voitureInitialisation(Voiture voiture, Model model, Agent agent){
        voiture.setModel(model);
        voiture.setCarburant("Diesel");
        voiture.setNombrePlaces(5);
        voiture.setNombrePortes(5);
        voiture.setClimatisation(true);
        voiture.setCouleur("Noire");
        voiture.setKilometrage(0);
        voiture.setDateMiseCirculation(LocalDate.now().minusDays(1));
        voiture.setMatricule("17588-A-49");
        voiture.setPosteAndroid(true);
        voiture.setDisponible(true);
        voiture.setPrixJour(200);
        voiture.setAgent(agent);
    }

    // Reservation Initialisation
    public static void reservationInitialisation(Reservation reservation, Voiture voiture, Client client){
        reservation.setDateDebut(LocalDateTime.now());
        reservation.setDateFin(LocalDateTime.now().plusDays(10));
        reservation.setEtat("En cours");
        reservation.setVoiture(voiture);
        reservation.setClient(client);
    }

    // Reduction Initialisation
    public static void reductionInitialisation(Reduction reduction){
        reduction.setDure("Petit");
        reduction.setTauxReduction(0);
    }

    // Location Initialisation
    public static void locationInitialisation(Location location, Reservation reservation, Reduction reduction){
        location.setReservation(reservation);
        location.setReduction(reduction);
        location.setDateDebut(LocalDateTime.now());
        location.setKilometrageDebut(5000);
    }

    // Frais Initialisation
    public static void fraisInitialisation(Frais frais, Voiture voiture){
        frais.setVoiture(voiture);
        frais.setDateEffet(LocalDateTime.now());
        frais.setDateEcheance(LocalDateTime.now().plusYears(1));
        frais.setMontant(2000);
    }

    // Entretien Initialisation
    public static void entretienInitialisation(Entretien entretien, Voiture voiture){
        entretien.setVoiture(voiture);
        entretien.setDescription("Vidange de voiture");
        entretien.setMontant(350);
        entretien.setDate(LocalDateTime.now());
    }

    // Vidange Initialisation
    public static void vidangeInitalisation(Vidange vidange, Voiture voiture){
        entretienInitialisation(vidange, voiture);
        vidange.setKilometrageMaximal(70000);
    }

}
