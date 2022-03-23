package com.negra.location.utility;

public class Pattern {

    public static final String PATTERN_USER_NAME = "^[a-zA-Z ]{3,30}$";
    public static final String PATTERN_USER_TEL = "^0[567][0-9]{8}$";
    public static final String PATTERN_USER_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$";
    public static final String PATTERN_USER_ROLE = "^ROLE_[a-zA-Z]{5,6}$";

    public static final String PATTERN_RS_AGENCE = "^[a-zA-Z0-9 ]{3,30}$";

    public static final String PATTERN_ADRESSE_RUE = "^[a-zA-Z0-9- ]{3,30}$";
    public static final String PATTERN_ADRESSE_QUARTIER = "^[a-zA-Z0-9- ]{3,30}$";
    public static final String PATTERN_ADRESSE_VILLE = "^[a-zA-Z- ]{3,30}$";
    public static final String PATTERN_ADRESSE_PAYS = "^[a-zA-Z- ]{3,30}$";

    public static final String PATTERN_MARQUE = "^[a-zA-Z ]{3,20}$";
    public static final String PATTERN_MODEL = "^[a-zA-Z0-9 ]{3,20}$";

    public static final String PATTERN_CARBURANT_LIBELLE = "^[a-zA-z]{6,7}$";

    public static final String PATTERN_VOITURE_MATRICULE = "^[1-9][0-9]{0,4}-[A-Z]-[1-9][0-9]?$";

    public static final String PATTERN_COLOR = "^#[0-9a-f]{6}$";

    public static final String PATTERN_DATE = "MM/dd/yyyy";

    public static final String PATTERN_DESCRIPTION = "^[a-zA-Z0-9 ]{3,100}$";

    public static final String PATTERN_DURATION = "^[a-zA-Z]{3,10}$";

    public static final String PATTERN_RESERVATION_STATE = "^[a-zA-Z ]{5,10}$";
}
