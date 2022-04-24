package com.negra.location.service.interfaces;

import com.negra.location.dto.*;
import com.negra.location.exception.*;
import com.negra.location.model.*;

import java.util.List;
import java.util.Map;

public interface ICarService {

    Map<String, Object> getHomePageListings() throws DataNotFoundException;

    Car createCar(CarCreationDto carCreationDto) throws DataNotFoundException, AlreadyExistsException, CurrentUserNotFoundException, UserNotFoundException, ClassCastException, DataStoreException;

    Car insertCar(Car car, Model model, Fuel fuel, Agent agent, Category category) throws DataStoreException;

    void verifyConstraintUniqueRegistrationNumber(String matricule) throws DataNotFoundException, AlreadyExistsException;

    CarDetailsDto getCarDetailsDto(Long id) throws DataNotFoundException, AccessDeniedException;

    Map<String, Object> findAll(int page, int size) throws DataStoreException;

    void deleteCar(Car car) throws DataStoreException;

    Map<String, Object> getListingDetailsDtoAndSimilarListingDtos(long id) throws DataNotFoundException;

    Car findById(Long id) throws DataNotFoundException;

    List<Long> getNotReservedCarIds();

    List<Car> findListingsWithoutDateConstraint(String markLibelle, String modelLibelle, String town);

    List<Car> getAvailableCars(HomeSearchCarDto homeSearchCarDto);

    Map<String, Object> getAgentCarDtos(int page, int size) throws CurrentUserNotFoundException;

    BookingCarDto getBookingCarDto(Car car) throws CurrentUserNotFoundException, AccessDeniedException;
}
