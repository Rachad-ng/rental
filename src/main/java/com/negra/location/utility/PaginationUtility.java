package com.negra.location.utility;

import com.negra.location.dto.ListingDto;
import com.negra.location.mapper.ListingDtoMapper;
import com.negra.location.model.Car;
import org.mapstruct.factory.Mappers;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public class PaginationUtility {

    private static final ListingDtoMapper listingDtoMapper = Mappers.getMapper(ListingDtoMapper.class);

    public static void initializeModelWithPaginationParameters(Model model, List<Car> availableCars, int currentPage, int size){

        HttpSession httpSession = SessionUtility.getSession();
        if(httpSession.getAttribute("availableCars") == null)
            httpSession.setAttribute("availableCars", availableCars);

        int numberOfPages = availableCars.size() / size;
        numberOfPages += (availableCars.size() % size == 0) ? 0 : 1;

        int nombreOfCars = availableCars.size();

        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("previousPage", currentPage - 1);
        model.addAttribute("nextPage", currentPage + 1);
        model.addAttribute("numberOfCars", nombreOfCars);
        model.addAttribute("numberOfCarPerPage", size);

        // lastIndex permet de gérer l'exception IndexOutOfBoundException
        int lastIndex = ((currentPage + 1) * size) <= nombreOfCars ? ((currentPage + 1) * size) : nombreOfCars;

        List<ListingDto> listingDtos = listingDtoMapper.carToListingDto(availableCars.subList(currentPage * size, lastIndex));

        model.addAttribute("listingDtos", listingDtos);
    }

}
