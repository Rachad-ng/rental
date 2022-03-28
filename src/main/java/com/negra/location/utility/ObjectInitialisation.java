package com.negra.location.utility;

import com.negra.location.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ObjectInitialisation {

    // User Initialisation
    public static void userInitialisation(User user){
        user.setLastName("Neggaz");
        user.setFirstname("Rachad");
        user.setTel("0707101019");
        user.setPassword("helloWorld");
        user.setActive(true);
    }

    // Admin initialisation
    public static void adminInitialisation(Administrator admin){
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
        agent.setNotoriety(NotorietyUtility.BRONZE);
    }

    // Client Initialisation
    public static void clientInitialisation(Client client){
        userInitialisation(client);
        client.setEmail("rachad-ng@gmail.com");
        client.setRole("ROLE_CLIENT");
        client.setLoyalty(0);
    }

    // Adresse Initialisation
    public static void adresseInitialisation(Address address, Agent agent){
        address.setNumber(1);
        address.setStreet("Lekbab");
        address.setDistrict("El Massira");
        address.setTown("Berkane");
        address.setCountry("Maroc");
        address.setAgent(agent);
    }

    // Marque Initialisation
    public static void marqueInitialisation(Mark mark){
        mark.setLibelle("Mercedes");
    }

    // Model Initialisation
    public static void modelInitialisation(Model model, Mark mark){
        model.setLibelle("C220");
        model.setMark(mark);
    }

    // Voiture Initialisation
    public static void voitureInitialisation(Car car, Model model, Agent agent){
        car.setModel(model);
 //       voiture.setCarburant("Diesel");
        car.setNumberOfPlaces(5);
        car.setNumberOfDoors(5);
        car.setAirConditioning(true);
        car.setColor("Noire");
        car.setMileage(0);
        car.setDateCirculation(LocalDate.now().minusDays(1));
        car.setRegistrationNumber("17588-A-49");
        car.setAndroidAvailable(true);
        car.setAvailable(true);
        car.setPricePerDay(200);
        car.setAgent(agent);
    }

    // Reservation Initialisation
    public static void reservationInitialisation(Reservation reservation, Car car, Client client){
        reservation.setStartDate(LocalDateTime.now());
        reservation.setBackDate(LocalDateTime.now().plusDays(10));
        reservation.setState("En cours");
        reservation.setCar(car);
        reservation.setClient(client);
    }

    // Reduction Initialisation
    public static void reductionInitialisation(Reduction reduction){
        reduction.setDuration("Petit");
        reduction.setReductionRate(0);
    }

    // Location Initialisation
    public static void locationInitialisation(Rental rental, Reservation reservation, Reduction reduction){
        rental.setReservation(reservation);
        rental.setReduction(reduction);
        rental.setStartDate(LocalDateTime.now());
        rental.setStartMileage(5000);
    }

    // Frais Initialisation
    public static void fraisInitialisation(Cost cost, Car car){
        cost.setCar(car);
        cost.setStartDate(LocalDateTime.now());
        cost.setDueDate(LocalDateTime.now().plusYears(1));
        cost.setAmount(2000);
    }

    // Entretien Initialisation
    public static void entretienInitialisation(Maintenance maintenance, Car car){
        maintenance.setCar(car);
        maintenance.setDescription("Vidange de voiture");
        maintenance.setAmount(350);
        maintenance.setDate(LocalDateTime.now());
    }

    // Vidange Initialisation
    public static void vidangeInitalisation(OilChange oilChange, Car car){
        entretienInitialisation(oilChange, car);
        oilChange.setMaxMileage(70000);
    }

}
