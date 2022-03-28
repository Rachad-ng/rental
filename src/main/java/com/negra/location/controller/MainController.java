package com.negra.location.controller;

import com.negra.location.dto.ListingCarDto;
import com.negra.location.dto.ListingDetailsCarDto;
import com.negra.location.service.interfaces.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private static final String HOME = "index";
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

    @GetMapping("/")
    public String home(Model model){
        Map<String, Object> data = new HashMap<>();
        data = carService.getHomePageCars();
        model.addAttribute("newCarAndModelWithImageDtos", data.get("newCarAndModelWithImageDtos"));
        model.addAttribute("bestOffreCarDtos", data.get("bestOffreCarDtos"));
        return HOME;
    }

    @GetMapping("/listings")
    public String listings(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "6") int size){

        Map<String, Object> data = carService.findAll(page, size);

        // Recuperation des voiture
        List<ListingCarDto> listingCarDtos = (List<ListingCarDto>) data.get("listingCarDtos");
        model.addAttribute("listingCarDtos", listingCarDtos);

        long numberOfCars = (long) data.get("numberOfCars");
        model.addAttribute("numberOfCars", numberOfCars);

        // Traitement du pagination
        int numberOfPages = (int) data.get("numberOfPages");

        int previousPage = (page > 0) ? page-1 : page;
        int nextPage = (page < numberOfPages-1) ? page+1 : page;

        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("numberOfCarPerPage", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);

        return LISTINGS;
    }

    @GetMapping("/listingsDetails")
    public String listingsDetails(@RequestParam int id, Model model){
        String result = LISTINGS_DETAILS;
        try{
            Map<String, Object> data = carService.getListingDetailsCarDtoAndSimilarListingCarDtos(id);
            ListingDetailsCarDto listingDetailsCarDto = (ListingDetailsCarDto) data.get("listingDetailsCarDto");
            List<ListingCarDto> similarListingCarDtos = (List<ListingCarDto>) data.get("similarListingCarDtos");

            model.addAttribute("listingDetailsCarDto", listingDetailsCarDto);
            model.addAttribute("similarListingCarDtos", similarListingCarDtos);
        }catch (Exception e){
            result = "redirect:/exp";
        }
        return result;
    }

/*    Creation d'admin

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AdministratorService administratorService;

    @RequestMapping("/test")
    public void addAdmin(){
        Administrator administrator = new Administrator();
        ObjectInitialisation.adminInitialisation(administrator);
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        administratorService.createAdministrateur(administrator);
    }

 */

}
