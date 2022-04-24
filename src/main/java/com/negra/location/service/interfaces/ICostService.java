package com.negra.location.service.interfaces;

import com.negra.location.exception.CostDateException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Cost;
import com.negra.location.model.Car;

import java.util.List;

public interface ICostService {

    void createCost(Cost cost, Car car) throws DataStoreException;
    void deleteCost(Cost cost) throws DataStoreException;

    List<Cost> findByCarId(Long idCar);
    void verifyCostDate(Cost cost, Long idCar) throws CostDateException;

}
