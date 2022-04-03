package com.negra.location.controller;

import com.negra.location.dto.HomeSearchCarDto;
import com.negra.location.dto.ListingDto;
import com.negra.location.dto.ListingDetailsDto;
import com.negra.location.exception.VisitNotCountedException;
import com.negra.location.model.Car;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.service.interfaces.ISearchService;
import com.negra.location.service.interfaces.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private IVisitService visitService;
    @Autowired
    private ISearchService searchService;

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
        HomeSearchCarDto homeSearchCarDto = searchService.initializeSearchCarForm();
        model.addAttribute("homeSearchCarDto", homeSearchCarDto);

        Map<String, Object> data = carService.getHomePageListings();
        model.addAttribute("mostPopularCarAndModelWithImageDtos", data.get("mostPopularCarAndModelWithImageDtos"));
        model.addAttribute("hotListingDtos", data.get("hotListingDtos"));

        return HOME;
    }

    @GetMapping("/listings")
    public String listings(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "6") int size){

        Map<String, Object> data = carService.findAll(page, size);

        // Recuperation des voiture
        List<ListingDto> listingDtos = (List<ListingDto>) data.get("listingDtos");
        model.addAttribute("listingDtos", listingDtos);

        long numberOfListings = (long) data.get("numberOfListings");
        model.addAttribute("numberOfListings", numberOfListings);

        // Traitement du pagination
        int numberOfPages = (int) data.get("numberOfPages");

        int previousPage = (page > 0) ? page-1 : page;
        int nextPage = (page < numberOfPages-1) ? page+1 : page;

        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("numberOfListingsPerPage", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("previousPage", previousPage);
        model.addAttribute("nextPage", nextPage);

        return LISTINGS;
    }

    @GetMapping("/listingsDetails")
    public String listingsDetails(@RequestParam Long id, Model model){
        String result = LISTINGS_DETAILS;
        try {
            Map<String, Object> data = carService.getListingDetailsDtoAndSimilarListingDtos(id);
            ListingDetailsDto listingDetailsDto = (ListingDetailsDto) data.get("listingDetailsDto");
            List<ListingDto> similarListingDtos = (List<ListingDto>) data.get("similarListingDtos");

            // visitService.initializeVisit(id);

            model.addAttribute("listingDetailsDto", listingDetailsDto);
            model.addAttribute("similarListingDtos", similarListingDtos);

        }catch(VisitNotCountedException e){
            // Le traitement de persistence de la visite n'est pas possible pour un propriétaire du l'annonce
        }catch (Exception e){
            result = "redirect:/exp";
        }
        visitService.initializeVisit(id);

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
