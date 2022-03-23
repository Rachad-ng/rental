package com.negra.location.controller;

import com.negra.location.dto.VoitureDto;
import com.negra.location.service.interfaces.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private static final String ABOUT = "about";
    private static final String LISTINGS = "listings";
    private static final String LISTINGS_DETAILS = "listings-details";
    private static final String HOW_IT_WORKS = "how-it-works";
    private static final String NEWS = "news";
    private static final String NEWS_DETAILS = "news-details";
    private static final String CONTACT = "contact";

    @Autowired
    private ICarService carService;

    @GetMapping ("/about")
    public String about(){
        return ABOUT;
    }

    @GetMapping("/listingsDetails")
    public String listingsDetails(){
        return LISTINGS_DETAILS;
    }

    @GetMapping("/news")
    public String news(){
        return NEWS;
    }

    @GetMapping("/newsDetails")
    public String newsDetails(){
        return NEWS_DETAILS;
    }

    @GetMapping("/contact")
    public String contact(){
        return CONTACT;
    }

    @GetMapping("/howItWorks")
    public String howItWorks(){
        return HOW_IT_WORKS;
    }

    @GetMapping("/listings")
    public String listings(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "6") int size){
        Map<String, Object> data = carService.findAll(page, size);

        // Recuperation des voiture
        List<VoitureDto> cars = (List<VoitureDto>) data.get("voitureDtos");
        model.addAttribute("cars", cars);

        long nombreCars = (long) data.get("nombreCars");
        model.addAttribute("nombreCars", nombreCars);

        // Traitement du pagination
        int nombrePages = (int) data.get("nombrePages");
        int previousPage = (page > 0) ? page-1 : page;
        int nextPage = (page < nombrePages-1) ? page+1 : page;

        model.addAttribute("nombrePages", nombrePages);
        model.addAttribute("nombreCarParPage", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);

        return LISTINGS;
    }
}
