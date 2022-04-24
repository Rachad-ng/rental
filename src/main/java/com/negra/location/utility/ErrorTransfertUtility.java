package com.negra.location.utility;

import com.negra.location.dto.BookingRequestDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static com.negra.location.utility.ErrorMessage.*;

public class ErrorTransfertUtility {


    public static void transfertLoginRequiredErrors(Model model){
        HttpSession httpSession = SessionUtility.getSession();
        if(httpSession.getAttribute("errorMessage") != null){
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }
    }

    public static HttpSession transfertErrors(BookingRequestDto bookingRequestDto, BindingResult bindingResult){

        LocalDate bookingStartDate = bookingRequestDto.getBookingStartDate();
        LocalDate bookingBackDate = bookingRequestDto.getBookingBackDate();

        HttpSession httpSession = SessionUtility.getSession();

        httpSession.setAttribute("bookingStartDate", bookingRequestDto.getBookingStartDate());
        httpSession.setAttribute("bookingBackDate", bookingRequestDto.getBookingBackDate());

        if(bindingResult.hasFieldErrors("bookingStartDate"))
            httpSession.setAttribute("bookingStartDateError", bindingResult.getFieldError("bookingStartDate").getCode());

        if(bindingResult.hasFieldErrors("bookingBackDate"))
            httpSession.setAttribute("bookingBackDateError", bindingResult.getFieldError("bookingBackDate").getCode());

        if(bookingBackDate.isBefore(bookingStartDate))
            httpSession.setAttribute("errorMessage", ERROR_BOOKING_BACK_DATE_BEFORE_START);

        return httpSession;
    }

    public static void transfertBookingRequestErrorMessagesToView(Model model){

        HttpSession httpSession = SessionUtility.getSession();

        if(httpSession.getAttribute("bookingStartDate") != null){

            model.addAttribute("bookingStartDate", httpSession.getAttribute("bookingStartDate"));
            model.addAttribute("bookingBackDate", httpSession.getAttribute("bookingBackDate"));

            httpSession.removeAttribute("bookingStartDate");
            httpSession.removeAttribute("bookingBackDate");

            if(httpSession.getAttribute("bookingStartDateError") != null){
                if(httpSession.getAttribute("bookingStartDateError").equals("Future"))
                    model.addAttribute("bookingStartDateError", ERROR_BOOKING_START_DATE_INVALID);
                else
                    model.addAttribute("bookingStartDateError", ERROR_BOOKING_START_DATE_REQUIRED);

                httpSession.removeAttribute("bookingStartDateError");
            }

            if(httpSession.getAttribute("bookingBackDateError") != null){
                if(httpSession.getAttribute("bookingBackDateError").equals("Future"))
                    model.addAttribute("bookingBackDateError", ERROR_BOOKING_BACK_DATE_INVALID);
                else
                    model.addAttribute("bookingBackDateError", ERROR_BOOKING_BACK_DATE_REQUIRED);

                httpSession.removeAttribute("bookingBackDateError");
            }
        }

        if(httpSession.getAttribute("errorMessage") != null){
            model.addAttribute("errorMessage", httpSession.getAttribute("errorMessage"));
            httpSession.removeAttribute("errorMessage");
        }
    }

}
