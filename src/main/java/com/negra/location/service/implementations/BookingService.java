package com.negra.location.service.implementations;

import com.negra.location.dto.BookingAgentDto;
import com.negra.location.dto.BookingClientDto;
import com.negra.location.exception.AccessDeniedException;
import com.negra.location.exception.CarNotAvailableException;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.mapper.BookingAgentDtoMapper;
import com.negra.location.mapper.BookingClientDtoMapper;
import com.negra.location.model.*;
import com.negra.location.repository.BookingRepository;
import com.negra.location.repository.CarRepository;
import com.negra.location.repository.UserRepository;
import com.negra.location.service.interfaces.IRentalService;
import com.negra.location.service.interfaces.IBookingService;
import com.negra.location.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.*;

import static com.negra.location.utility.ErrorMessage.*;

@AllArgsConstructor
@Service
@Transactional
public class BookingService implements IBookingService {

    private BookingRepository bookingRepository;
    private IRentalService locationService;
    private CarRepository carRepository;
    private UserRepository userRepository;
    private BookingAgentDtoMapper bookingAgentDtoMapper;
    private BookingClientDtoMapper bookingClientDtoMapper;
    private IUserService userService;

    @Override
    public void createBooking(Booking booking, User user, Car car) throws DataStoreException {

        user.addBooking(booking);
        car.addReservation(booking);

        try{
            bookingRepository.save(booking);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    @Override
    public void isCarAvailable(Long idCar, LocalDate startDate, LocalDate backDate) throws DataNotFoundException, CarNotAvailableException {

        boolean isNotInBookingList, reservedCarButAvailableForThisPeriod;

        try{
            isNotInBookingList = carRepository.getNotReservedCarIds().contains(idCar);
        }catch (Exception e){
            throw new DataNotFoundException(ERROR_DATA);
        }

        // S'il est existe des réservations pour cette voiture , on vérifie la disponibilité dans la data souhaité, sinn la voiture est disponible
        if(!isNotInBookingList){
            List<Long> ids;

            try{
                ids = bookingRepository.getNotReservedCarIds(startDate, backDate);
            }catch (Exception e){
                throw new DataNotFoundException(ERROR_DATA);
            }

            reservedCarButAvailableForThisPeriod = ids.contains(idCar);
            if(!reservedCarButAvailableForThisPeriod)
                throw new CarNotAvailableException(ERROR_BOOKING_ALREADY_RESERVED_EXCEPTION);
        }
    }

    @Override
    public Booking findById(Long id) throws DataNotFoundException {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if(optionalBooking.isPresent())
            return bookingRepository.findById(id).get();
        else
            throw new DataNotFoundException(ERROR_DATA);
    }

    @Override
    public void verifyBookingAccess(Booking booking) throws AccessDeniedException {
        Long currentUserId = userService.getCurrentUser().getId();
        Long agentId = booking.getCar().getAgent().getId();
        Long clientId = booking.getUser().getId();

        if(currentUserId != agentId && currentUserId != clientId)
            throw new AccessDeniedException(ACCESS_DENIED);
    }

    @Override
    public void verifyBookingAgentAccess(Booking booking) throws AccessDeniedException {
        Long currentUserId = userService.getCurrentUser().getId();
        Long agentId = booking.getCar().getAgent().getId();

        if(currentUserId != agentId)
            throw new AccessDeniedException(ACCESS_DENIED);
    }

    @Override
    public void deleteBooking(Booking booking){
        booking.getUser().removeBooking(booking);
        booking.getCar().removeReservation(booking);

        Rental rental = booking.getRental();

        try{
            if(rental != null)
                locationService.deleteLocation(rental);

            bookingRepository.delete(booking);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public List<Long> getBookingsCarIds(LocalDate startDate) {
        return bookingRepository.getNotReservedCarIds(startDate);
    }

    @Override
    public List<Long> getBookingsCarIds(LocalDate startDate, LocalDate backDate) {
        return bookingRepository.getNotReservedCarIds(startDate, backDate);
    }

    @Override
    public Map<String, Object> getClientBookingDtos(Long idUser, int page, int size) throws DataNotFoundException {

        Page<Booking> userBookingsPage = bookingRepository.getClientBookings(idUser, PageRequest.of(page, size));
        Map<String, Object> data = new HashMap<>();

        List<Booking> userBookings = userBookingsPage.getContent();

        if(userBookings.size() > 0){

            List<BookingClientDto> bookingClientDtos = bookingClientDtoMapper.bookingToBookingClientDto(userBookings);
            data.put("bookingClientDtos", bookingClientDtos);

             // Required data for pagination
            data.put("totalBookings", userBookingsPage.getTotalElements());
            data.put("totalPages", userBookingsPage.getTotalPages());
            data.put("numberOfBookingPerPage", size);
            data.put("currentPage", page);
        }else
            data.put("bookingsNotFoundInfoMessage", BOOKING_NOT_FOUND_INFO_MESSAGE);

        return data;
    }

    @Override
    public Map<String, Object> getAgentBookingRequestDtos(Long idUser, int page, int size) throws DataNotFoundException {

        Page<Booking> userBookingsPage = userBookingsPage = bookingRepository.getAgentBookings(idUser, PageRequest.of(page, size));;
        Map<String, Object> data = new HashMap<>();

        List<Booking> userBookings = userBookingsPage.getContent();

        if(userBookings.size() > 0){

            List<BookingAgentDto> bookingAgentDtos = bookingAgentDtoMapper.bookingToBookingAgentDto(userBookings);
            data.put("bookingAgentDtos", bookingAgentDtos);


            // Required data for pagination
            data.put("totalBookings", userBookingsPage.getTotalElements());
            data.put("totalPages", userBookingsPage.getTotalPages());
            data.put("numberOfBookingPerPage", size);
            data.put("currentPage", page);
        }else
            data.put("bookingsNotFoundInfoMessage", BOOKING_NOT_FOUND_INFO_MESSAGE);

        return data;
    }

    @Override
    public void confirmBooking(Long id) throws DataNotFoundException, AccessDeniedException {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if(optionalBooking.isPresent()){
            Booking booking = optionalBooking.get();
            verifyBookingAgentAccess(booking);

            bookingRepository.confirmBooking(id);
        }else{
            throw new DataNotFoundException(ERROR_DATA);
        }
    }

    @Override
    public int getCarNumberOfBookingsInProgress(Long idCar) {
        return bookingRepository.getCarNumberOfBookingsInProgress(idCar);
    }

}
