package com.negra.location.controller;
import com.negra.location.dto.CarCreationDto;
import com.negra.location.exception.*;
import com.negra.location.service.interfaces.IAgentService;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.utility.DataUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static com.negra.location.utility.ErrorMessage.*;

@Controller
@RequestMapping("/agent")
public class AgentController {

    private static final String NEW_CARR = "newCar";
    private static final String LISTINGS = "listings";

    @Autowired
    private ICarService voitureService;
    @Autowired
    private IAgentService agentService;

    @GetMapping("/newCar")
    public String newCarForm(Model model){
        String result = NEW_CARR;
        try {
            agentService.initialisationCarCreationFrom(model);
        }catch(DataStoreException e){
            result = "redirect:/exp";
        }
        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM/dd/yyyy"), true));
    }

    @PostMapping("/newCar")
    public String addCar(@Valid CarCreationDto carCreationDto, BindingResult bindingResult, Model model){

        String result = NEW_CARR;
        if(!bindingResult.hasErrors()){
            try {
                /* Traitement temporaire de resolution du format de date
                    Conversion du champ dateCirculation de String en Local date
                    Affecter sa valeur au champs dateMiseCirculation
               */
                DataUtility.dateCreationCarFormatter(carCreationDto);

                voitureService.createCar(carCreationDto);
                result = "redirect:/listings";
            } catch(DateTimeParseException | DateConstraintException e){
                model.addAttribute("errorDateMessage", ERROR_CAR_DATE_MISE_CIRCULATION);
            } catch (AlreadyExistsException | MapperException | CurrentUserNotFoundException | UserNotFoundException e) {
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("errorDateOrMatriculeMessage", e.getMessage());
            } catch (ClassCastException e) {
                model.addAttribute("errorMessage", ERROR_DATA_CASTING);
            } catch (DataStoreException e) {
                result = "redirect:/exp";
            }finally {
                if(!result.equals("redirect:/exp")){
                    try{
                        agentService.recuperationInputData(carCreationDto, model);
                    }catch(Exception e){
                        result = "redirect:/exp";
                    }
                }
            }
        }else{
           try {
               agentService.recuperationInputData(carCreationDto, model);
           }catch(Exception e){
               result = "redirect:/exp";
           }
        }
        return result;
    }

    @GetMapping("/listings")
    public String listingCars(){
        return LISTINGS;
    }

}
