package com.negra.location.utility;

public class ErrorMessage {

    public static final String ERROR_SEND_DATA = "Erreur d'envoie des données.!!";

    // Marque Errors
    public static final String ERROR_MARK_LIBELLE = "La marque est invalide.!!";
    public static final String ERROR_MARK_NOT_FOUND = "La marque n'existe pas.!!";

    // Model Errors
    public static final String ERROR_MODEL_LIBELLE = "Le modele est invalide.!!";
    public static final String ERROR_MODEL_REFERENCE_PRICE = "Le prix de refence n'est pas valide";

    // Car Errors
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
    public static final String AGENT_CARS_NOT_FOUND_INFO_MESSAGE = "Vous n'avez aucune voiture pour le moment!";

    // Fuel errors
    public static final String ERROR_FUEL_NOT_FOUND = "Le carburant est invalide.!!";

    // User Errors
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
    public static final String ERROR_CURRENT_USER_NOT_FOUND = "Vous devez se connecter pour effectuer cette opération.!";

    // Client Errors
    public static final String ERROR_CLIENT_NOT_FOUND = "Le client n'existe pas";
    public static final String ERROR_CLIENT_LOYALTY = "Le nombre des points de fidelite est invalide.!!";

    // Agent Errors
    public static final String ERROR_AGENT_RS = "Le RS est invalid.!!";
    public static final String ERROR_AGENT_NOTORIETY = "La reputation invalide.!!";

    // Booking Errors
    public static final String ERROR_BOOKING_START_DATE_REQUIRED = "Vous devez saisir la date de depart.!!";
    public static final String ERROR_BOOKING_START_DATE_INVALID = "Vous devez saisir une date de depart postérieure.!!";
    public static final String ERROR_BOOKING_BACK_DATE_REQUIRED = "Vous devez saisir la date de retour.!!";
    public static final String ERROR_BOOKING_BACK_DATE_INVALID = "Vous devez saisir une date de retour postérieure.!!";
    public static final String ERROR_BOOKING_BACK_DATE_BEFORE_START = "La date de retour doit étre postérieure à celle de debut.!!";
    public static final String ERROR_DATA_TO_VALID_BOOKING_REQUIRED = "Erreur lors de traitement des données.!!";

    public static final String ERROR_BOOKING_ALREADY_RESERVED_EXCEPTION = "La voiture est déja réservée dans cette date.!!";
    public static final String ERROR_BOOKING_CLIENT_REQUIRED = "Vous devez se connecter en tant que client.!!";

    public static final String ERROR_BOOKING_STATE_REQUIRED = "La reservation est sans état.!!";
    public static final String ERROR_BOOKING_STATE_INVALID = "L'etat de reservation est invalide.!!";
    public static final String BOOKING_NOT_FOUND_INFO_MESSAGE = "Vous n'avez aucune reservation pour le moment";

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
    public static final String ERROR_COST_START_DATE_REQUIRED = "Vous devez saisir la date d'effet!!";
    public static final String ERROR_COST_START_DATE_INVALID = "La date d'effet est invalide.!!";
    public static final String ERROR_COST_END_DATE_REQUIRED = "Vous devez saisir la date d'echeance.!!";
    public static final String ERROR_COST_END_DATE_INVALID = "La date d'echeance est invalide.!!";
    public static final String ERROR_COST_END_DATE_IS_BEFORE = "Il exisite déjà une date ultérieure";
    public static final String ERROR_COST_AMOUNT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String ERROR_COST_AMOUNT_INVALID = "Le montant est invalid.!! Ressayez..";
    public static final String ERROR_COST_TYPE = "Le type de charge est invalid.!!";

    // Assurance Errors
    public static final String ERROR_ASSURANCE_START_DATE_REQUIRED = "Vous devez saisir la date d'effet d'assurance.!!";
    public static final String ERROR_ASSURANCE_START_DATE_INVALID = "La date d'effet d'assurance est invalide.!!";
    public static final String ERROR_ASSURANCE_END_DATE_REQUIRED = "Vous devez saisir la date d'echeance.!!";
    public static final String ERROR_ASSURANCE_END_DATE_INVALID = "La date d'echeance est invalide.!!";
    public static final String ERROR_ASSURANCE_AMOUNT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String ERROR_ASSURANCE_AMOUNT_INVALID = "Le montant est invalid.!!";

    // Technical visit Errors
    public static final String ERROR_TECHNICAL_VISIT_START_DATE_REQUIRED = "Vous devez saisir la date d'effet de visite technique.!!";
    public static final String ERROR_TECHNICAL_VISIT_START_DATE_INVALID = "La date d'effet de visite technique est invalide.!!";
    public static final String ERROR_TECHNICAL_VISIT_END_DATE_REQUIRED = "Vous devez saisir la date d'echeance.!!";
    public static final String ERROR_TECHNICAL_VISIT_END_DATE_INVALID = "La date d'echeance est invalide.!!";
    public static final String ERROR_TECHNICAL_VISIT_AMOUNT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String ERROR_TECHNICAL_VISIT_AMOUNT_INVALID = "Le montant est invalid.!!";

    // Maintenance Errors
    public static final String ERROR_MAINTENANCE_DATE_REQUIRED = "Vous devez saisir la date d'entretien.!!";
    public static final String ERROR_MAINTENANCE_DATE_INVALID = "La date d'entretient est invalide.!!";
    public static final String ERROR_MAINTENANCE_AMOUNT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String ERROR_MAINTENANCE_AMOUNT_INVALID = "Le montant est invalid.!!";
    public static final String ERROR_MAINTENANCE_COMMENT_REQUIRED = "Vous devez saisir le commentaire.!!";
    public static final String ERROR_MAINTENANCE_COMMENT_INVALID = "Le commentaire est invalid.!!";

    // Oil-change Errors
    public static final String ERROR_OIL_CHANGE_MILEAGE_MAX_REQUIRED = "Vous devez saisir le kilometrage maximal du vidange.!!";
    public static final String ERROR_OIL_CHANGE_MILEAGE_MAX_INVALID = "Le kilometrage maximal est invalid.!!";
    public static final String ERROR_OIL_CHANGE_MILEAGE_MAX_ALREADY_EXISIT = "Un kilometrage maximal plus grand existe déja.!!";

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
    public static final String ACCESS_DENIED = "Vous n'êtes pas autorisé.!!";
    public static final String ERROR_DATA_CASTING = "Le traitement est échoué.!! Ressayez...";
    public static final String ERROR_DATA_STORING = "Le serveur des données ne répond pas pour le moment.!! Ressayez plus tard...";
    public static final String ERROR_DATA = "Le traitement des données est échouée.!!";

}
