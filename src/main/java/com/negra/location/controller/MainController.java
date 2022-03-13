package com.negra.location.controller;

import com.negra.location.entity.*;

import com.negra.location.service.AdresseService;
import com.negra.location.service.UtilisateurService;
import com.negra.location.utility.ObjectInitialisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static com.negra.location.utility.ErrorMessage.ERROR_UTILISATEUR_CONFIRMATION_PASSWORD;

@Controller
public class MainController {

    private static final String SIGNIN = "signin";
    private static final String LOGIN = "login";
    private static final String AGENT_PAGE = "agent";
    private static final String CLIENT_PAGE = "client";

    // Injection des services
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private AdresseService adresseService;

    @GetMapping("/signin")
    public String getSigninForm(Model model){

        // Creation des objets nécessaires à la validation de formulaire

        model.addAttribute("client", new Client());
        model.addAttribute("agent", new Agent());
        model.addAttribute("adresse", new Adresse());

        // Adaptation de formulaire pour une inscription d'un client (Cacher les inputs dédiés à l'inscription d'un agent)

        model.addAttribute("clientRole", true);
        model.addAttribute("agentRole", false);

        // On va utiliser cette attribut pour le message d'erreur de la confirmation de mot de passe
        model.addAttribute("errorConfirmationPwd", null);

        return SIGNIN;
    }

    @PostMapping("/signinAgent")
    public String signinAgent(@Valid Agent agent, BindingResult bindingResultAgent, @Valid Adresse adresse, BindingResult bindingResultAdresse, Model model, @RequestParam String passwordConfirm){
        String result = SIGNIN;

        if(!bindingResultAgent.hasErrors() && !bindingResultAdresse.hasErrors() && passwordConfirm.equals(agent.getPassword())){
            utilisateurService.createUtilisateur(agent);
            adresseService.createAdresse(adresse, agent);
            result = AGENT_PAGE;
        }
        else{
            model.addAttribute("client", new Client());
            model.addAttribute("agent", agent);
            model.addAttribute("adresse", adresse);

            // Choisir le type du compte agent
            model.addAttribute("clientRole", false);
            model.addAttribute("agentRole", true);

            // Verification de la confirmation de mot de passe
            if(passwordConfirm.equals(agent.getPassword()))
                model.addAttribute("errorConfirmationPwd", null);
            else
                model.addAttribute("errorConfirmationPwd", ERROR_UTILISATEUR_CONFIRMATION_PASSWORD);
        }

        return result;
    }

    @PostMapping("/signinClient")
    public String signinClien(@Valid Client client, BindingResult bindingResult, Model model, @RequestParam String passwordConfirm){
        String result = SIGNIN;

        if(!bindingResult.hasErrors() && passwordConfirm.equals(client.getPassword())){
            utilisateurService.createUtilisateur(client);
            result = CLIENT_PAGE;
        }
        else{
            model.addAttribute("client", client);
            model.addAttribute("agent", new Agent());
            model.addAttribute("adresse", new Adresse());

            // Choisir le type du compte client
            model.addAttribute("clientRole", true);
            model.addAttribute("agentRole", false);

            // Verification de la confirmation de mot de passe
            if(passwordConfirm.equals(client.getPassword()))
                model.addAttribute("errorConfirmationPwd", null);
            else
                model.addAttribute("errorConfirmationPwd", ERROR_UTILISATEUR_CONFIRMATION_PASSWORD);
        }

        return result;
    }

    @GetMapping("/login")
    public String login(){
        return LOGIN;
    }

    @GetMapping("/test")
    public String test(){
        Administrateur administrateur = new Administrateur();
        ObjectInitialisation.adminInitialisation(administrateur);
        utilisateurService.createUtilisateur(administrateur);
        return "layout";
    }




/*    Validation Beans, Verification des containtes de DB lors de l'ajout ou suppression

    @Autowired
    private MarqueService marqueService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private VoitureService voitureService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReductionService reductionService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private FraisService fraisService;
    @Autowired
    private EntretienService entretienService;

    @GetMapping("/validation")
    public String validateBeans(){

        // Objects Creation
        Administrateur administrateur = new Administrateur();
        Agent agent = new Agent();
        Client client = new Client();
        Adresse adresse = new Adresse();
        Marque marque = new Marque();
        Model model = new Model();
        Voiture voiture = new Voiture();
        Reservation reservation = new Reservation();
        Reduction reduction = new Reduction();
        Location location = new Location();
        Frais frais = new Assurance();
        Vidange vidange = new Vidange();


        // Objects Initialisation
        ObjectInitialisation.adminInitialisation(administrateur);
        ObjectInitialisation.marqueInitialisation(marque);
        ObjectInitialisation.modelInitialisation(model, marque);
        ObjectInitialisation.agentInitialisation(agent);
        ObjectInitialisation.adresseInitialisation(adresse, agent);
        ObjectInitialisation.voitureInitialisation(voiture, model, agent);
        ObjectInitialisation.clientInitialisation(client);
        ObjectInitialisation.reservationInitialisation(reservation, voiture, client);
        ObjectInitialisation.reductionInitialisation(reduction);
        ObjectInitialisation.locationInitialisation(location,reservation, reduction);
        ObjectInitialisation.fraisInitialisation(frais, voiture);
        ObjectInitialisation.vidangeInitalisation(vidange, voiture);

        // Objects Validation
        Validation.validateBeans(administrateur);
        Validation.validateBeans(marque);
        Validation.validateBeans(model);
        Validation.validateBeans(agent);
        Validation.validateBeans(adresse);
        Validation.validateBeans(voiture);
        Validation.validateBeans(client);
        Validation.validateBeans(reservation);
        Validation.validateBeans(reduction);
        Validation.validateBeans(location);
        Validation.validateBeans(frais);
        Validation.validateBeans(vidange);

        // Persistance des données
        utilisateurService.createUtilisateur(administrateur);
        utilisateurService.createUtilisateur(agent);
        utilisateurService.createUtilisateur(client);
        adresseService.createAdresse(adresse, agent);
        marqueService.createMarque(marque);
        modelService.createModel(model, marque);
        voitureService.createVoiture(voiture, model, agent);
        reservationService.createReservation(reservation, client, voiture);
        reductionService.createReduction(reduction);
        locationService.createLocation(location, reservation, reduction);
        fraisService.createFrais(frais, voiture);
        entretienService.createEntretien(vidange, voiture);

        // Test Violation des contraintes
        fraisService.deleteFrais((frais));
        entretienService.deleteEntretien(vidange);

        return SIGNIN;
    }
*/

}
