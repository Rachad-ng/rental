package com.negra.location.service.interfaces;

import com.negra.location.exception.AccessDeniedException;
import com.negra.location.exception.CarNotAvailableException;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Booking;
import com.negra.location.model.Car;
import com.negra.location.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IBookingService {

    void createBooking(Booking booking, User user, Car car) throws DataStoreException;

    void isCarAvailable(Long idCar, LocalDate startDate, LocalDate backDate) throws DataNotFoundException, CarNotAvailableException;

    Booking findById(Long id) throws DataNotFoundException;

    void verifyBookingAccess(Booking booking) throws AccessDeniedException;

    void verifyBookingAgentAccess(Booking booking) throws AccessDeniedException;

    void deleteBooking(Booking booking) throws DataStoreException;

    List<Long> getBookingsCarIds(LocalDate startDate);

    List<Long> getBookingsCarIds(LocalDate startDate, LocalDate backDate);

    Map<String, Object> getClientBookingDtos(Long id, int page, int size) throws DataNotFoundException;

    Map<String, Object> getAgentBookingRequestDtos(Long id, int page, int size) throws DataNotFoundException;

    void confirmBooking(Long id) throws DataNotFoundException;

    int getCarNumberOfBookingsInProgress(Long idCar);
}
