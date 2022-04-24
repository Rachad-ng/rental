package com.negra.location.controller;

import com.negra.location.dto.HomeSearchCarDto;
import com.negra.location.exception.DateException;
import com.negra.location.model.Car;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.utility.PaginationUtility;
import com.negra.location.utility.SessionUtility;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
public class SearchController {

    private static final String HOME = "index";
    private static final String SEARCH_RESULT_PAGE = "search-result";

    private ICarService carService;

    @GetMapping("/searchCar")
    public String searchCar(Model model, @RequestParam(name = "page", defaultValue = "0") int currentPage, @RequestParam(name = "size", defaultValue = "6") int size){
        String result;

        HttpSession httpSession = SessionUtility.getSession();
        Object availableCarsObject = httpSession.getAttribute("availableCars");

        if(availableCarsObject == null){
            httpSession.invalidate();
            result = "redirect:/";
        }else{
            List<Car> availableCars = (List<Car>) availableCarsObject;
            PaginationUtility.initializeModelWithPaginationParameters(model, availableCars, currentPage, size);
            result = SEARCH_RESULT_PAGE;
        }

        return result;
    }

    @PostMapping("/searchCar")
    public String searchCar(@Valid HomeSearchCarDto homeSearchCarDto, BindingResult bindingResult, Model model) {

        String result;

        if(!bindingResult.hasErrors()) {
            try{
                List<Car> availableCars = carService.getAvailableCars(homeSearchCarDto);

                // Gestion de pagination en se basant sur des variable de sessions
                PaginationUtility.initializeModelWithPaginationParameters(model, availableCars, 0, 6);

                result = SEARCH_RESULT_PAGE;

            }catch(DateException e){
                model.addAttribute("dateError", e.getMessage());
                result = HOME;
            }
        }else
            result = "redirect:/exp";

        return result;
    }

}
