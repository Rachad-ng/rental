package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.exception.OilChangeMaxMileageException;
import com.negra.location.model.Maintenance;
import com.negra.location.model.Car;

import java.util.List;

public interface IMaintenanceService {

    void createMaintenance(Maintenance maintenance, Car car) throws OilChangeMaxMileageException, DataStoreException;
    void deleteMaintenance(Maintenance maintenance) throws DataStoreException;

    List<Maintenance> findByCarId(Long idCar);
    void verifyMaxMileage(Maintenance maintenance, Car car) throws OilChangeMaxMileageException;

}
