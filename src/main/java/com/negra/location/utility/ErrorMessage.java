package com.negra.location.utility;

public class ErrorMessage {

    public static final String ERROR_SEND_DATA = "Erreur d'envoie des données.!!";

    // Marque Errors
    public static final String ERROR_MARK_LIBELLE = "La marque est invalide.!!";
    public static final String ERROR_MARK_NOT_FOUND = "La marque n'existe pas.!!";

    // Model Errors
    public static final String ERROR_MODEL_LIBELLE = "Le modele est invalide.!!";

    // Voiture Errors
    public static final String ERROR_CAR_PLACES_NUMBER = "Le nombre de places est invalide.!!";
    public static final String ERROR_CAR_DOORS_NUMBER = "Le nombre de portes est invalide.!!";
    public static final String ERROR_CAR_COLOR = "La couleur est invalide.!!";
    public static final String ERROR_CAR_DATE_MISE_CIRCULATION = "La date de mise en circulation est invalide.!!";
    public static final String ERROR_CAR_KILOMETRAGE = "Le kilometrage est invalide.!!";
    public static final String ERROR_CAR_PRICE = "Le prix est invalide.!!";
    public static final String ERROR_CAR_MODEL = "Le model est invalide";
    public static final String ERROR_CAR_ENGINE = "Le carburant est invalide.!!";
    public static final String ERROR_CAR_CREATION_MAPPING = "La création du voiture est échoué.!! Ressayez...";
    public static final String ERROR_CAR_MATRICULE = "Le matricule est invalide.!!";
    public static final String ERROR_CAR_MATRICLE_ALREADY_EXISTS = "Il existe déjà ce matricule.!! Si vous étes sur, veuillez nous contacter..";

    // Carburant errors
    public static final String ERROR_CARBURANT_NOT_FOUND = "Le carburant est invalide.!!";

    // Utilisateur Errors
    public static final String ERROR_USER_EMAIL_EMPTY = "Vous devez saisir l'adresse email.!!";
    public static final String ERROR_USER_EMAIL_INVALID = "L'adresse email est invalide.!!";
    public static final String ERROR_USER_PASSWORD_INVALID = "Le mot de passe est invalid.!!";
    public static final String ERROR_USER_ROLE_INVALID = "Le role est inalid.!!";
    public static final String ERROR_USER_TELE_INVALID = "Le numero de telephone est invalid.!!";
    public static final String ERROR_USER_NOM_INVALID = "Le nom est invalid.!!";
    public static final String ERROR_USER_PRENOM_INVALID = "Le prenom est inalid.!!";
    public static final String ERROR_USER_REGISTRATION_DATE_REQUIRED = "La date d'inscription n'est pas attribué.!!";
    public static final String ERROR_USER_REGISTRATION_DATE_INVALID = "La date d'inscription est invalide.!!";

    public static final String ERROR_USER_PASSWORD_CONFIRMATION = "La confirmation est échoué.!!";

    public static final String ERROR_USER_ALREADY_EXISTS = "L'adresse email existe auparavant.!!";
    public static final String ERROR_USER_NOT_FOUND = "Aucun compte lié à cet email.!!";
    public static final String ERROR_CURRENT_USER_NOT_FOUND = "La recuperation de vos est échoué.!! Ressayez...";

    // Client Errors
    public static final String ERROR_CLIENT_FIDELITY = "Le nombre des points de fidelite est invalide.!!";

    // Agent Errors
    public static final String ERROR_AGENT_RS = "Le RS est invalid.!!";
    public static final String ERROR_AGENT_REPUTATION = "La reputation invalide.!!";

    // Reservation Errors
    public static final String ERROR_RESERVATION_START_DATE_REQUIRED = "Vous devez saisir la date de depart de reservation.!!";
    public static final String ERROR_RESERVATION_START_DATE_INVALID = "La date de depart de reservation est invalide.!!";
    public static final String ERROR_RESERVATION_END_DATE_REQUIRED = "Vous devez saisir la date de retour de reservation.!!";
    public static final String ERROR_RESERVATION_END_DATE_INVALID = "La date de retour de reservation est invalide.!!";
    public static final String ERROR_RESERVATION_STATE_REQUIRED = "La reservation est sans etat.!!";
    public static final String ERROR_RESERVATION_STATE_INVALID = "L'etat de reservation est invalide.!!";

    // Location Errors
    public static final String ERROR_LOCATION_START_DATE_REQUIRED = "Vous devez saisir la date de depart de location.!!";
    public static final String ERROR_LOCATION_START_DATE_INVALID = "La date de depart de location est invalide.!!";
    public static final String ERROR_LOCATION_END_DATE_REQUIRED = "Vous devez saisir la date de retour de location.!!";
    public static final String ERROR_LOCATION_END_DATE_INVALID = "La date de retour de location est invalide.!!";
    public static final String ERROR_LOCATION_START_KILOMETRAGE_REQUIRED = "Vous devez saisir le kilometrage de depart.!!";
    public static final String ERROR_LOCATION_START_KILOMETRAGE_INVALID = "Le kilometrage de depart est invalid.!!";
    public static final String ERROR_LOCATION_END_KILOMETRAGE_REQUIRED = "Vous devez saisir le kilometrage de retour.!!";
    public static final String ERROR_LOCATION_END_KILOMETRAGE_INVALID = "Le kilometrage de retour est invalid.!!";

    // Adresse Errors
    public static final String ERROR_ADRESSE_NUMBER_REQUIRED = "Vous devez saisir le numero de porte.!!";
    public static final String ERROR_ADRESSE_NUMBER_INVALID = "Le numero du porte est invalid";
    public static final String ERROR_ADRESSE_RUE_REQUIRED = "Vous devez saisir la rue.!!";
    public static final String ERROR_ADRESSE_RUE_INVALID = "la rue est invalide.!!";
    public static final String ERROR_ADRESSE_QUARTIER_REQUIRED = "Vous devez saisir le quartier.!!";
    public static final String ERROR_ADRESSE_QUARTIER_INVALID = "le quartier est invalid.!!";
    public static final String ERROR_ADRESSE_VILLE_REQUIRED = "Vous devez saisir la ville.!!";
    public static final String ERROR_ADRESSE_VILLE_INVALID = "la ville est invalide.!!";
    public static final String ERROR_ADRESSE_PAYS_REQUIRED = "Vous devez saisir le pays.!!";
    public static final String ERROR_ADRESSE_PAYS_INVALID = "le pays est invalid.!!";

    // Frais Errors
    public static final String ERROR_FRAIS_DATE_EFFET_REQUIRED = "Vous devez saisir la date d'effet.!!";
    public static final String  ERROR_FRAIS_DATE_EFFET_INVALID = "La date d'effet est invalide.!!";
    public static final String ERROR_FRAIS_DATE_ECHEANCE_REQUIRED = "Vous devez saisir la date d'echeance.!!";
    public static final String  ERROR_FRAIS_DATE_ECHEANCE_INVALID = "La date d'echeance est invalide.!!";
    public static final String  ERROR_FRAIS_MONTANT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String  ERROR_FRAIS_MONTANT_INVALID = "Le montant est invalid.!!";

    // Entretien Errors
    public static final String ERROR_ENTRETIEN_DATE_REQUIRED = "Vous devez saisir la date d'entretien.!!";
    public static final String ERROR_ENTRETIEN_DATE_INVALID = "La date d'entretient est invalide.!!";
    public static final String  ERROR_ENTRETIENT_MONTANT_REQUIRED = "Vous devez saisir le montant.!!";
    public static final String  ERROR_ENTRETIENT_MONTANT_INVALID = "Le montant est invalid.!!";
    public static final String  ERROR_ENTRETIENT_DESCRIPTION_REQUIRED = "Vous devez saisir la description.!!";
    public static final String  ERROR_ENTRETIENT_DESCRIPTION_INVALID = "La description est invalide.!!";

    // Vidange Errors
    public static final String ERROR_VIDANGE_KILOMETRAGE_MAX_REQUIRED = "Vous devez saisir le kilometrage maximal.!!";
    public static final String ERROR_VIDANGE_KILOMETRAGE_MAX_INVALID = "Le kilometrage maximal est invalid.!!";

    // Reduction Errors
    public static final String ERROR_REDUCTION_DURE_REQUIRED = "Vous devez saisir la dure.!!";
    public static final String ERROR_REDUCTION_DURE_INVALID = "La dure est invalid.!!";
    public static final String ERROR_REDUCTION_TAUX_REQUIRED = "Vous devez saisir la taux de la reduction.!!";
    public static final String ERROR_REDUCTION_TAUX_INVALID = "Le taux est invalid.!!";

    // Categorie Errors
    public static final String ERROR_CATEGORIE_NOT_FOUND = "La categorie choisi n'existe pas.!!";

    // Global Errors
    public static final String ERROR_DATA_CASTING = "Le traitement est échoué.!! Ressayez...";
    public static final String ERROR_DATA_STORING = "Le serveur des données ne répond pas pour le moment.!! Ressayez plus tard...";
    public static final String ERROR_DATA = "Le traitement des données est échouée.!!";

}
