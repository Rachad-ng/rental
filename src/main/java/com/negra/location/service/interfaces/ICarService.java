package com.negra.location.service.interfaces;

import com.negra.location.dto.CarCreationDto;
import com.negra.location.exception.*;
import com.negra.location.model.*;

import java.util.Map;

public interface ICarService {

    void createCar(CarCreationDto carCreationDto) throws DataNotFoundException, AlreadyExistsException, CurrentUserNotFoundException, UserNotFoundException, ClassCastException, DataStoreException;
    void insertCar(Car car, Model model, Fuel fuel, Agent agent, Category category) throws DataStoreException;
    Car findByRegistrationNumber(String matricule) throws AlreadyExistsException;
    Map<String, Object> findAll(int page, int size) throws DataStoreException;
    void deleteCar(Car car) throws DataStoreException;

}
