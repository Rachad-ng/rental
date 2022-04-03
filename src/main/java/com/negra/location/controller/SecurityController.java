package com.negra.location.controller;

import com.negra.location.dto.AgentRegistrationDto;
import com.negra.location.dto.ClientRegistrationDto;
import com.negra.location.dto.UserLoginDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.service.interfaces.IAgentService;
import com.negra.location.service.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static com.negra.location.utility.ErrorMessage.ERROR_USER_PASSWORD_CONFIRMATION;

@Controller
public class SecurityController {

    private static final String HOME = "/";
    private static final String SIGNUP = "signup";
    private static final String LOGIN = "login";
    private static final String ACCES_DENIED_PAGE = "403";

    // Injection des services
    @Autowired
    private IAgentService agentService;
    @Autowired
    private IClientService clientService;

    @GetMapping("/signup")
    public String signup(Model model){

        // Creation des objets nécessaires à la validation de formulaire

        model.addAttribute("agentRegistrationDto", new AgentRegistrationDto());
        model.addAttribute("clientRegistrationDto", new ClientRegistrationDto());

        // Adaptation de formulaire pour une inscription d'un client (Cacher les inputs dédiés à l'inscription d'un agent)

        model.addAttribute("clientRole", true);
        model.addAttribute("agentRole", false);

        return SIGNUP;
    }


    @PostMapping("/signupAgent")
    public String signupAgent(@Valid AgentRegistrationDto agentRegistrationDto, BindingResult bindingResultAgent, Model model){

        if(!bindingResultAgent.hasErrors()){
            if(agentRegistrationDto.getPassword().equals(agentRegistrationDto.getPasswordConfirmed())){
                try{
                    agentService.createAgent(agentRegistrationDto);
                    return  "redirect:/" + LOGIN;
                }catch (AlreadyExistsException e){
                    model.addAttribute("alreadyExistsErrorMessage", e.getMessage());
                }catch (DataStoreException e){
                    model.addAttribute("systemError", e.getMessage());
                }
            }else
                model.addAttribute("errorConfirmationPwd", ERROR_USER_PASSWORD_CONFIRMATION);
        }
        model.addAttribute("agentRegistrationDto", agentRegistrationDto);
        model.addAttribute("clientRegistrationDto", new ClientRegistrationDto());

        // Choisir le type du compte agent
        model.addAttribute("clientRole", false);
        model.addAttribute("agentRole", true);

        return SIGNUP;
    }


    @PostMapping("/signupClient")
    public String signinClient(@Valid ClientRegistrationDto clientRegistrationDto, BindingResult bindingResult, Model model){

        if(!bindingResult.hasErrors()){
            if(clientRegistrationDto.getPassword().equals(clientRegistrationDto.getPasswordConfirmed())){
                try {
                    clientService.createClient(clientRegistrationDto);
                    return  "redirect:/" + LOGIN;
                }catch (AlreadyExistsException e){
                    model.addAttribute("alreadyExistsErrorMessage", e.getMessage());
                }catch(DataStoreException e){
                    model.addAttribute("systemError", e.getMessage());
                }
            }else
                model.addAttribute("errorConfirmationPwd", ERROR_USER_PASSWORD_CONFIRMATION);
        }

        model.addAttribute("clientRegistrationDto", clientRegistrationDto);
        model.addAttribute("agentRegistrationDto", new AgentRegistrationDto());

        // Choisir le type du compte client
        model.addAttribute("clientRole", true);
        model.addAttribute("agentRole", false);

        return SIGNUP;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model){
        model.addAttribute("userLoginDto", new UserLoginDto());
        return LOGIN;
    }

    @RequestMapping("/403")
    public String accessDenied(){
        return ACCES_DENIED_PAGE;
    }

}
