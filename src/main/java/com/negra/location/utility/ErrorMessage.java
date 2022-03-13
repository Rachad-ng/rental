package com.negra.location.utility;

public class ErrorMessage {

    public static final String ERROR_SENDS_DATA = "Erreur d'envoie des données.!!";

    // Marque Errors
    public static final String ERROR_MARQUE_LIBELLE = "La marque est invalide.!!";

    // Model Errors
    public static final String ERROR_MODEL_LIBELLE = "Le modele est invalide.!!";

    // Voiture Errors
    public static final String ERROR_VOITURE_CARBURANT = "Le carburant est invalide.!!";
    public static final String ERROR_VOITURE_NOMBRE_PLACES = "Le nombre de places est invalide.!!";
    public static final String ERROR_VOITURE_NOMBRE_PORTES = "Le nombre de portes est invalide.!!";
    public static final String ERROR_VOITURE_COULEUR = "La couleur est invalide.!!";
    public static final String ERROR_VOITURE_MATRICULE = "Le matricule est invalide.!!";
    public static final String ERROR_VOITURE_DATE_MISE_CIRCULATION = "La date de mise en circulation est invalide.!!";
    public static final String ERROR_VOITURE_KILOMETRAGE = "Le kilometrage est invalide.!!";
    public static final String ERROR_VOITURE_PRIX = "Le prix est invalide.!!";

    // Utilisateur Errors
    public static final String ERROR_UTILISATEUR_EMAIL_EMPTY = "Vous devez saisir l'adresse email.!!";
    public static final String ERROR_UTILISATEUR_EMAIL_INVALID = "L'adresse email est invalide.!!";
    public static final String ERROR_UTILISATEUR_PASSWORD_INVALID = "Le mot de passe est invalid.!!";
    public static final String ERROR_UTILISATEUR_ROLE_INVALID = "Le role est inalid.!!";
    public static final String ERROR_UTILISATEUR_TELE_INVALID = "Le numero de telephone est invalid.!!";
    public static final String ERROR_UTILISATEUR_NOM_INVALID = "Le nom est invalid.!!";
    public static final String ERROR_UTILISATEUR_PRENOM_INVALID = "Le prenom est inalid.!!";
    public static final String ERROR_UTILISATEUR_DATE_INSCRIPTION_REQUIRED = "La date d'inscription n'est pas attribué.!!";
    public static final String ERROR_UTILISATEUR_DATE_INSCRIPTION_INVALID = "La date d'inscription est invalide.!!";

    public static final String ERROR_UTILISATEUR_CONFIRMATION_PASSWORD = "La confirmation est échoué.!!";

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

}
