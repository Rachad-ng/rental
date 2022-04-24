package com.negra.location.controller;

import com.google.gson.Gson;
import com.negra.location.dto.*;
import com.negra.location.exception.*;
import com.negra.location.mapper.BookingCreateRentalDtoMapper;
import com.negra.location.mapper.RentalCreationReductionDtoMapper;
import com.negra.location.model.*;
import com.negra.location.service.interfaces.IBookingService;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.service.interfaces.IReductionService;
import com.negra.location.service.interfaces.IUserService;
import com.negra.location.utility.ErrorTransfertUtility;
import com.negra.location.utility.SessionUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.negra.location.utility.ErrorMessage.ACCESS_DENIED;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA;

@AllArgsConstructor
@Controller
public class BookingController {

    private static final String AGENT_BOOKINGS = "agent-bookings";
    private static final String CLIENT_BOOKINGS = "client-bookings";
    private static final String CLOSE_BOOKING_FORM_PAGE = "close-booking";
    private static final String ERROR_PAGE = "404";

    private IBookingService bookingService;
    private ICarService carService;
    private IUserService userService;
    private IReductionService reductionService;
    private RentalCreationReductionDtoMapper rentalCreationReductionDtoMapper;
    private BookingCreateRentalDtoMapper bookingCreateRentalDtoMapper;

    @PostMapping("/bookingRequest")
    public String bookingRequest(@Valid BookingRequestDto bookingRequestDto, BindingResult bindingResult, Model model){
        String result = "redirect:/client/bookings";

        LocalDate bookingStartDate = bookingRequestDto.getBookingStartDate();
        LocalDate bookingBackDate = bookingRequestDto.getBookingBackDate();

        if(!bindingResult.hasErrors() && bookingBackDate.isAfter(bookingStartDate)){

            // Traitement du verification de disponibilité, et persistence du demande de reservation.
            HttpSession httpSession = SessionUtility.getSession();

            Long idCar = bookingRequestDto.getIdCar();
            try{
                User user = userService.getCurrentUser();
                bookingService.isCarAvailable(idCar, bookingStartDate, bookingBackDate);

                // Persistance de la demande de reservation
                Car car = carService.findById(idCar);
                Booking booking = new Booking(bookingStartDate, bookingBackDate);
                bookingService.createBooking(booking, user, car);

                if(user instanceof Agent){
                    result = "redirect:/agent/bookings";
                }else if (user instanceof Administrator){
                    result = "redirect:/admin/bookings";
                }

            }catch(DataNotFoundException | CarNotAvailableException e){
                httpSession.setAttribute("errorMessage", e.getMessage());
                result = "redirect:/listingsDetails?id=" + bookingRequestDto.getIdCar() + "#booking-form";
            }catch (CurrentUserNotFoundException e){
                httpSession.setAttribute("errorMessage", e.getMessage());
                result = "redirect:/login";
            }

        }else{
            // L'erreur est au niveau des dates
            if(!bindingResult.hasFieldErrors("idCar")){
                ErrorTransfertUtility.transfertErrors(bookingRequestDto, bindingResult);
                result = "redirect:/listingsDetails?id=" + bookingRequestDto.getIdCar() + "#booking-form";
            }
            else
                result = "redirect:/exp";     // Si l'utilisateur a changé l'id , on le redirige à la page d'erreurs
        }

        return result;
    }

    @GetMapping("/agent/booking-requests")
    public String agentBookingRequests(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "3") int size, Model model){

        Agent agent = (Agent) userService.getCurrentUser();
        // Recuperation des reservation
        Map<String, Object> data = bookingService.getAgentBookingRequestDtos(agent.getId(), page, size);

        if(data.get("bookingAgentDtos") != null){
            model.addAttribute("bookingAgentDtos", data.get("bookingAgentDtos"));
            model.addAttribute("totalBookings", data.get("totalBookings"));
            model.addAttribute("totalPages", data.get("totalPages"));
            model.addAttribute("numberOfBookingPerPage", data.get("numberOfBookingPerPage"));
            model.addAttribute("currentPage", data.get("currentPage"));
        }else
            model.addAttribute("bookingsNotFoundInfoMessage", data.get("bookingsNotFoundInfoMessage"));

        return AGENT_BOOKINGS;
    }

    @GetMapping("/agent/bookings")
    public String agentBookings(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "3") int size, Model model){

        User currentUser = userService.getCurrentUser();
        // Recuperation des reservation
        Map<String, Object> data = bookingService.getClientBookingDtos(currentUser.getId(), page, size);

        if(data.get("bookingClientDtos") != null){
            model.addAttribute("bookingClientDtos", data.get("bookingClientDtos"));
            model.addAttribute("totalBookings", data.get("totalBookings"));
            model.addAttribute("totalPages", data.get("totalPages"));
            model.addAttribute("numberOfBookingPerPage", data.get("numberOfBookingPerPage"));
            model.addAttribute("currentPage", data.get("currentPage"));
        }else
            model.addAttribute("bookingsNotFoundInfoMessage", data.get("bookingsNotFoundInfoMessage"));

        return CLIENT_BOOKINGS;
    }

    @GetMapping("/client/bookings")
    public String clientBookings(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "3") int size, Model model){

        User currentUser = userService.getCurrentUser();
        // Recuperation des reservation
        Map<String, Object> data = bookingService.getClientBookingDtos(currentUser.getId(), page, size);

        if(data.get("bookingClientDtos") != null){
            model.addAttribute("bookingClientDtos", data.get("bookingClientDtos"));
            model.addAttribute("totalBookings", data.get("totalBookings"));
            model.addAttribute("totalPages", data.get("totalPages"));
            model.addAttribute("numberOfBookingPerPage", data.get("numberOfBookingPerPage"));
            model.addAttribute("currentPage", data.get("currentPage"));
        }else
            model.addAttribute("bookingsNotFoundInfoMessage", data.get("bookingsNotFoundInfoMessage"));

        return CLIENT_BOOKINGS;
    }

    @PutMapping("/agent/confirm-booking")
    public @ResponseBody String confirmBooking(@RequestParam(name = "idBooking") Long idBooking) {
        Map<String, Object> data = new HashMap<>();
        try{
            bookingService.confirmBooking(idBooking);
            data.put("successMessage", "La confirmation est effectuée avec success");
        }catch(DataNotFoundException | AccessDeniedException e){
            data.put("errorMessage", e.getMessage());
        }
        return new Gson().toJson(data);
    }

    @GetMapping("/agent/close-booking")
    public String closeBooking(@RequestParam(name = "id") Long idBooking, Model model){

        String result;
        try{
            Booking booking = bookingService.findById(idBooking);
            BookingCreateRentalDto bookingCreateRentalDto = bookingCreateRentalDtoMapper.bookingToBookingCreateRentalDto(booking);
            model.addAttribute("bookingCreateRentalDto", bookingCreateRentalDto);

            List<Reduction> reductions = reductionService.findAll();
            List<RentalCreationReductionDto> rentalCreationReductionDtoList = rentalCreationReductionDtoMapper.reductionToRentalCreationReductionDto(reductions);
            model.addAttribute("rentalCreationReductionDtoList", rentalCreationReductionDtoList);

            result = CLOSE_BOOKING_FORM_PAGE;
        }catch(DataNotFoundException e){
            result = ERROR_PAGE;
        }

        return result;
    }

    @DeleteMapping("/delete-booking")
    public @ResponseBody void deleteBooking(@RequestParam(name = "idBooking") Long id){
        Booking booking;
        try {
            booking = bookingService.findById(id);
            User user = userService.getCurrentUser();
            Long idAgent = booking.getCar().getAgent().getId();
            Long idClient = booking.getUser().getId();
            Long idCurrentUser = user.getId();

            if(idCurrentUser == idAgent || idCurrentUser == idClient)
                bookingService.deleteBooking(booking);
            else
                throw new AccessDeniedException(ACCESS_DENIED);

        }catch (DataNotFoundException | DataStoreException e){
            throw new DataNotFoundException(ERROR_DATA);
        }catch (CurrentUserNotFoundException e){
            throw new CurrentUserNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/agent/booking-car-info")
    public @ResponseBody String getBookingCarInfo(@RequestParam("idBooking") Long idBooking){

        Map<String, Object> data = new HashMap<>();
        try {
            Booking booking = bookingService.findById(idBooking);

            BookingCarDto bookingCarDto = carService.getBookingCarDto(booking.getCar());

            data.put("bookingCarDto", bookingCarDto);
        }catch (DataNotFoundException | CurrentUserNotFoundException | AccessDeniedException e){
            data.put("errorMessage", e.getMessage());
        }
        return new Gson().toJson(data);
    }

    @GetMapping("/agent/booking-client-info")
    public @ResponseBody String getClientInfos(@RequestParam("idBooking") Long idBooking){

        Map<String, Object> data = new HashMap<>();

        try {
            Booking booking = bookingService.findById(idBooking);
            bookingService.verifyBookingAccess(booking);

            ClientDto clientDto = userService.getUserShortInfo(booking.getUser().getId());
            data.put("clientDto", clientDto);
        }catch (DataNotFoundException | AccessDeniedException e){
            data.put("errorMessage", e.getMessage());
        }

        return new Gson().toJson(data);
    }

    @GetMapping("/agent/booking-manager")
    private @ResponseBody String manageBooking(@RequestParam("idBooking") Long idBooking, @RequestParam("idBooking") String operation){

        Map<String, Object> data = new HashMap<>();

        try {
            Booking booking = bookingService.findById(idBooking);
            bookingService.verifyBookingAgentAccess(booking);

            // data.put("serverMessage", message);

        }catch (DataNotFoundException | AccessDeniedException e){
            data.put("errorMessage", e.getMessage());
        }

        return new Gson().toJson(data);
    }
}
