package com.negra.location.controller;

import com.negra.location.dto.BookingRequestDto;
import com.negra.location.dto.ListingDetailsDto;
import com.negra.location.dto.ListingDto;
import com.negra.location.exception.VisitNotCountedException;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.service.interfaces.IVisitService;
import com.negra.location.utility.ErrorTransfertUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class ListingController {

    private static final String LISTINGS = "listings";
    private static final String LISTINGS_DETAILS = "listings-details";

    private ICarService carService;
    private IVisitService visitService;

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

        ListingDetailsDto listingDetailsDto = null;
        List<ListingDto> similarListingDtos = null;
        try {
            Map<String, Object> data = carService.getListingDetailsDtoAndSimilarListingDtos(id);
            listingDetailsDto = (ListingDetailsDto) data.get("listingDetailsDto");
            similarListingDtos = (List<ListingDto>) data.get("similarListingDtos");

            visitService.initializeVisit(id);

        }catch(VisitNotCountedException e){
            // Le traitement de persistence de la visite n'est pas possible pour un propriétaire du l'annonce
        }catch (Exception e){
            result = "redirect:/exp";
        }

        model.addAttribute("listingDetailsDto", listingDetailsDto);
        model.addAttribute("similarListingDtos", similarListingDtos);
        model.addAttribute("bookingRequestDto", new BookingRequestDto(id));

        // Dans le cas de recupération des erreurs de demande de reservation
        ErrorTransfertUtility.transfertBookingRequestErrorMessagesToView(model);

        return result;
    }

}
