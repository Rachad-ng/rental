package com.negra.location.controller;

import com.negra.location.dto.BookingRequestDto;
import com.negra.location.dto.CarCreationDto;
import com.negra.location.dto.CarDetailsDto;
import com.negra.location.exception.*;
import com.negra.location.model.Car;
import com.negra.location.service.interfaces.IAgentService;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.utility.ErrorTransfertUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA_CASTING;

@AllArgsConstructor
@Controller
public class CarController {

    private static final String NEW_CARR = "newCar";
    private static final String AGENT_CARS = "agent-cars";

    private IAgentService agentService;
    private ICarService carService;

    @GetMapping("/agent/newCar")
    public String newCarForm(Model model){
        String result = NEW_CARR;
        try {
            agentService.initialisationCarCreationFrom(model);
        }catch(DataStoreException e){
            result = "redirect:/exp";
        }
        return result;
    }

    @PostMapping("/agent/newCar")
    public String addCar(@Valid CarCreationDto carCreationDto, BindingResult bindingResult, Model model){

        String result = NEW_CARR;
        if(!bindingResult.hasErrors()){
            try {
                Car car = carService.createCar(carCreationDto);
                result = "redirect:/agent/car/" + car.getId();
            } catch (AlreadyExistsException | MapperException | CurrentUserNotFoundException | UserNotFoundException e) {
                model.addAttribute("errorMessage", e.getMessage());
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

    @GetMapping("/agent/car/{id}")
    public String carManagerPage(@PathVariable("id") Long id, Model model){
        String result = "car-manager";

        try{
            CarDetailsDto carDetailsDto = carService.getCarDetailsDto(id);

            model.addAttribute("carDetailsDto", carDetailsDto);
            model.addAttribute("bookingRequestDto", new BookingRequestDto(id));

            // Dans le cas de recupération des erreurs de demande de reservation
            ErrorTransfertUtility.transfertBookingRequestErrorMessagesToView(model);

        }catch (DataNotFoundException e){
            result = "redirect:/exp";
        }catch (AccessDeniedException e){
            result = "redirect:/403";
        }

        return result;
    }

    @GetMapping("/agent/cars")
    public String getCars(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "3") int size, Model model){

        // Recuperation des voitures
        Map<String, Object> data = carService.getAgentCarDtos(page, size);

        if(data.get("agentCarDtos") != null){
            model.addAttribute("agentCarDtos", data.get("agentCarDtos"));
            model.addAttribute("totalCars", data.get("totalCars"));
            model.addAttribute("totalPages", data.get("totalPages"));
            model.addAttribute("numberOfCarPerPage", data.get("numberOfCarPerPage"));
            model.addAttribute("currentPage", data.get("currentPage"));
        }else
            model.addAttribute("carsNotFoundInfoMessage", data.get("carsNotFoundInfoMessage"));

        return AGENT_CARS;
    }

}
