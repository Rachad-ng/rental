package com.negra.location.utility;

public class ErrorMessage {

    public static final String ERROR_SEND_DATA = "Erreur d'envoie des données.!!";

    // Marque Errors
    public static final String ERROR_MARK_LIBELLE = "La marque est invalide.!!";
    public static final String ERROR_MARK_NOT_FOUND = "La marque n'existe pas.!!";

    // Model Errors
    public static final String ERROR_MODEL_LIBELLE = "Le modele est invalide.!!";
    public static final String ERROR_MODEL_REFERENCE_PRICE = "Le prix de refence n'est pas valide";

    // Voiture Errors
    public static final String ERROR_CAR_PLACES_NUMBER = "Le nombre de places est invalide.!!";
    public static final String ERROR_CAR_DOORS_NUMBER = "Le nombre de portes est invalide.!!";
    public static final String ERROR_CAR_COLOR = "La couleur est invalide.!!";
    public static final String ERROR_CAR_DATE_CIRCULATION = "La date de mise en circulation est invalide.!!";
    public static final String ERROR_CAR_MILEAGE = "Le kilometrage est invalide.!!";
    public static final String ERROR_CAR_PRICE = "Le prix est invalide.!!";
    public static final String ERROR_CAR_MODEL = "Le model est invalide";
    public static final String ERROR_CAR_FUEL = "Le carburant est invalide.!!";
    public static final String ERROR_CAR_CREATION_MAPPING = "La création du voiture est échoué.!! Ressayez...";
    public static final String ERROR_CAR_REGISTRATION_NUMBER = "Le matricule est invalide.!!";
    public static final String ERROR_CAR_REGISTRATION_NUMBER_ALREADY_EXISTS = "Il existe déjà ce matricule.!! Si vous étes sur, veuillez nous contacter..";
    public static final String ERROR_CAR_NOT_FOUND = "La voiture que vous cherchiez n'existe pas.!! Ressayez..";

    // Carburant errors
    public static final String ERROR_FUEL_NOT_FOUND = "Le carburant est invalide.!!";

    // Utilisateur Errors
    public static final String ERROR_USER_EMAIL_EMPTY = "Vous devez saisir l'adresse email.!!";
    public static final String ERROR_USER_EMAIL_INVALID = "L'adresse email est invalide.!!";
    public static final String ERROR_USER_PASSWORD_INVALID = "Le mot de passe est invalid.!!";
    public static final String ERROR_USER_ROLE_INVALID = "Le role est inalid.!!";
    public static final String ERROR_USER_TEL_INVALID = "Le numero de telephone est invalid.!!";
    public static final String ERROR_USER_LASTNAME_INVALID = "Le nom est invalid.!!";
    public static final String ERROR_USER_FIRSTNAME_INVALID = "Le prenom est inalid.!!";
    public static final String ERROR_USER_REGISTRATION_DATE_REQUIRED = "La date d'inscription n'est pas attribué.!!";
    public static final String ERROR_USER_REGISTRATION_DATE_INVALID = "La date d'inscription est invalide.!!";

    public static final String ERROR_USER_PASSWORD_CONFIRMATION = "La confirmation est échoué.!!";

    public static final String ERROR_USER_ALREADY_EXISTS = "L'adresse email existe auparavant.!!";
    public static final String ERROR_USER_NOT_FOUND = "Aucun compte lié à cet email.!!";
    public static final String ERROR_CURRENT_USER_NOT_FOUND = "La recuperation de vos est échoué.!! Ressayez...";

    // Client Errors
    public static final String ERROR_CLIENT_LOYALTY = "Le nombre des points de fidelite est invalide.!!";

    // Agent Errors
    public static final String ERROR_AGENT_RS = "Le RS est invalid.!!";
    public static final String ERROR_AGENT_NOTORIETY = "La reputation invalide.!!";

    // Reservation Errors
    public static final String ERROR_RESERVATION_START_DATE_REQUIRED = "Vous devez saisir la date de depart de reservation.!!";
    public static final String ERROR_RESERVATION_START_DATE_INVALID = "La date de depart de reservation est invalide.!!";
    public static final String ERROR_RESERVATION_END_DATE_REQUIRED = "Vous devez saisir la date de retour de reservation.!!";
    public static final String ERROR_RESERVATION_END_DATE_INVALID = "La date de retour de reservation est invalide.!!";
    public static final String ERROR_RESERVATION_STATE_REQUIRED = "La reservation est sans etat.!!";
    public static final String ERROR_RESERVATION_STATE_INVALID = "L'etat de reservation est invalide.!!";

    // Rental Errors
    public static final String ERROR_RENTAL_START_DATE_REQUIRED = "Vous devez saisir la date de depart de location.!!";
    public static final String ERROR_RENTAL_START_DATE_INVALID = "La date de depart de location est invalide.!!";
    public static final String ERROR_RENTAL_END_DATE_REQUIRED = "Vous devez saisir la date de retour de location.!!";
    public static final String ERROR_RENTAL_END_DATE_INVALID = "La date de retour de location est invalide.!!";
    public static final String ERROR_RENTAL_START_MILEAGE_REQUIRED = "Vous devez saisir le kilometrage de depart.!!";
    public static final String ERROR_RENTAL_START_MILEAGE_INVALID = "Le kilometrage de depart est invalid.!!";
    public static final String ERROR_RENTAL_END_MILEAGE_REQUIRED = "Vous devez saisir le kilometrage de retour.!!";
    public static final String ERROR_RENTAL_END_MILEAGE_INVALID = "Le kilometrage de retour est invalid.!!";

    // Address Errors
    public static final String ERROR_ADDRESS_NUMBER_REQUIRED = "Vous devez saisir le numero de porte.!!";
    public static final String ERROR_ADDRESS_NUMBER_INVALID = "Le numero du porte est invalid";
    public static final String ERROR_ADDRESS_STREET_REQUIRED = "Vous devez saisir la rue.!!";
    public static final String ERROR_ADDRESS_STREET_INVALID = "la rue est invalide.!!";
    public static final String ERROR_ADDRESS_DISTRICT_REQUIRED = "Vous devez saisir le quartier.!!";
    public static final String ERROR_ADDRESS_DISTRICT_INVALID = "le quartier est invalid.!!";
    public static final String ERROR_ADDRESS_TOWN_REQUIRED = "Vous devez saisir la ville.!!";
    public static final String ERROR_ADDRESS_TOWN_INVALID = "la ville est invalide.!!";
    public static final String ERROR_ADDRESS_COUNTRY_REQUIRED = "Vous devez saisir le pays.!!";
    public static final String ERROR_ADDRESS_COUNTRY_INVALID = "le pays est invalid.!!";

    // Cost Errors
    public static final String ERROR_COST_START_DATE_REQUIRED = "Vous devez saisir la date d'effet.!!";
    public static final String ERROR_COST_START_DATE_INVALID = "La date d'effet est invalide.!!";
    public static final String ERROR_COST_END_DATE_REQUIRED = "Vous devez saisir la date d'echeance.!!";
    public static final String ERROR_COST_END_DATE_INVALID = "La date d'echeance est invalide.!!";
    public static final String ERROR_COST_AMOUNT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String ERROR_COST_AMOUNT_INVALID = "Le montant est invalid.!!";

    // Maintenance Errors
    public static final String ERROR_MAINTENANCE_DATE_REQUIRED = "Vous devez saisir la date d'entretien.!!";
    public static final String ERROR_MAINTENANCE_DATE_INVALID = "La date d'entretient est invalide.!!";
    public static final String ERROR_MAINTENANCE_AMOUNT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String ERROR_MAINTENANCE_AMOUNT_INVALID = "Le montant est invalid.!!";
    public static final String ERROR_MAINTENANCE_DESCRIPTION_REQUIRED = "Vous devez saisir la description.!!";
    public static final String ERROR_MAINTENANCE_DESCRIPTION_INVALID = "La description est invalide.!!";

    // Oil-change Errors
    public static final String ERROR_OIL_CHANGE_MILEAGE_MAX_REQUIRED = "Vous devez saisir le kilometrage maximal.!!";
    public static final String ERROR_OIL_CHANGE_MILEAGE_MAX_INVALID = "Le kilometrage maximal est invalid.!!";

    // Reduction Errors
    public static final String ERROR_REDUCTION_DURATION_REQUIRED = "Vous devez saisir la dure.!!";
    public static final String ERROR_REDUCTION_DURATION_INVALID = "La dure est invalid.!!";
    public static final String ERROR_REDUCTION_RATE_REQUIRED = "Vous devez saisir la taux de la reduction.!!";
    public static final String ERROR_REDUCTION_RATE_INVALID = "Le taux est invalid.!!";

    // Category Errors
    public static final String ERROR_CATEGORY_NOT_FOUND = "La categorie choisi n'existe pas.!!";

    // Visit Errors
    public static final String ERROR_VISIT_NOT_COUNTED = "La visite d'un agent à ses propres annonces n'est pas prise en compte";

    // Global Errors
    public static final String ERROR_DATA_CASTING = "Le traitement est échoué.!! Ressayez...";
    public static final String ERROR_DATA_STORING = "Le serveur des données ne répond pas pour le moment.!! Ressayez plus tard...";
    public static final String ERROR_DATA = "Le traitement des données est échouée.!!";

}
