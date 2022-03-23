package com.negra.location.dto;

import com.negra.location.model.Mark;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.negra.location.utility.ErrorMessage.*;
import static com.negra.location.utility.Pattern.PATTERN_COLOR;
import static com.negra.location.utility.Pattern.PATTERN_VOITURE_MATRICULE;

@Data
public class CarCreationDto implements Serializable {

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CAR_PLACES_NUMBER)
    private int nombrePlaces;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CAR_DOORS_NUMBER)
    private int nombrePortes;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean climatisation;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_COLOR, message = ERROR_CAR_COLOR)
    private String couleur;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean posteAndroid;

    @NotNull(message = ERROR_SEND_DATA)
    private boolean boiteAuto;

    @NotNull(message = ERROR_SEND_DATA)
    @Pattern(regexp = PATTERN_VOITURE_MATRICULE, message = ERROR_CAR_MATRICULE)
    private String matricule;

    /*
    @NotNull(message = ERROR_SENDS_DATA)
    @Past(message = ERROR_CAR_DATE_MISE_CIRCULATION)
    @DateTimeFormat(pattern = "mm/dd/yyyy")    */
    private LocalDate dateMiseCirculation;

    @NotNull(message = ERROR_SEND_DATA)
    private String dateCirculation;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_KILOMETRAGE)
    private int kilometrage;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 0, message = ERROR_CAR_PRICE)
    private int prixJour;

    @NotNull(message = ERROR_CAR_MODEL)
    @Min(value = 1, message = ERROR_CAR_MODEL)
    private long idMark;

    @NotNull(message = ERROR_CAR_MODEL)
    @Min(value = 1, message = ERROR_CAR_MODEL)
    private long idModel;

    @NotNull(message = ERROR_CAR_ENGINE)
    @Min(value = 1, message = ERROR_CAR_ENGINE)
    private long idCarburant;

    @NotNull(message = ERROR_SEND_DATA)
    @Min(value = 1, message = ERROR_CATEGORIE_NOT_FOUND)
    private long idCategorie;

    private List<Mark> marks = new ArrayList<>();

    private List<ModelDto> modelDtos = new ArrayList<>();

    private List<FuelDto> fuelDtos = new ArrayList<>();

    private List<CategorieDto> categorieDtos = new ArrayList<>();

}
