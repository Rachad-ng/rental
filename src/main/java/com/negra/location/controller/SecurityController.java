package com.negra.location.controller;

import com.negra.location.dto.AgentRegistrationDto;
import com.negra.location.dto.ClientRegistrationDto;
import com.negra.location.dto.UserLoginDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.service.AgentService;
import com.negra.location.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class SecurityController {

    private static final String HOME = "/";
    private static final String SIGNIN = "signin";
    private static final String AGENT_HOME = "agent/home";
    private static final String CLIENT_HOME = "client/home";
    private static final String LOGIN = "login";
    private static final String ACCES_DENIED_PAGE = "403";

    // Injection des services
    @Autowired
    private AgentService agentService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/signin")
    public String signin(Model model){

        // Creation des objets nécessaires à la validation de formulaire

        model.addAttribute("agentRegistrationDto", new AgentRegistrationDto());
        model.addAttribute("clientRegistrationDto", new ClientRegistrationDto());

        // Adaptation de formulaire pour une inscription d'un client (Cacher les inputs dédiés à l'inscription d'un agent)

        model.addAttribute("clientRole", true);
        model.addAttribute("agentRole", false);

        // On va utiliser cette attribut pour le message d'erreur de la confirmation de mot de passe
        model.addAttribute("errorConfirmationPwd", null);

        return SIGNIN;
    }


    @PostMapping("/signinAgent")
    public String signinAgent(@Valid AgentRegistrationDto agentRegistrationDto, BindingResult bindingResultAgent, Model model){

        if(!bindingResultAgent.hasErrors() && agentRegistrationDto.getPassword().equals(agentRegistrationDto.getPasswordConfirmed())){
            try{
                agentService.createAgent(agentRegistrationDto);
                return  "redirect:/" + AGENT_HOME;
            }catch (AlreadyExistsException e){
                model.addAttribute("alreadyExistsErrorMessage", e.getMessage());
            }
        }
        model.addAttribute("agentRegistrationDto", agentRegistrationDto);
        model.addAttribute("clientRegistrationDto", new ClientRegistrationDto());

        // Choisir le type du compte agent
        model.addAttribute("clientRole", false);
        model.addAttribute("agentRole", true);

        return SIGNIN;
    }


    @PostMapping("/signinClient")
    public String signinClien(@Valid ClientRegistrationDto clientRegistrationDto, BindingResult bindingResult, Model model){

        if(!bindingResult.hasErrors() && clientRegistrationDto.getPassword().equals(clientRegistrationDto.getPasswordConfirmed())){
            try {
                clientService.createClient(clientRegistrationDto);
                return  "redirect:/" + CLIENT_HOME;
            }catch (AlreadyExistsException e){
                model.addAttribute("alreadyExistsErrorMessage", "Ce compte existe.!!");
            }
        }

        model.addAttribute("clientRegistrationDto", clientRegistrationDto);
        model.addAttribute("agentRegistrationDto", new AgentRegistrationDto());

        // Choisir le type du compte client
        model.addAttribute("clientRole", true);
        model.addAttribute("agentRole", false);

        return SIGNIN;
    }


    @GetMapping("/login")
    public String getLoginForm(Model model){
        model.addAttribute("userLoginDto", new UserLoginDto());
        return LOGIN;
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:" + HOME;
    }


    @RequestMapping("/403")
    public String accessDenied(){
        return ACCES_DENIED_PAGE;
    }

}
