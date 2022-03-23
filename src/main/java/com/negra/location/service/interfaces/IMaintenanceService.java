package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Maintenance;
import com.negra.location.model.Car;

public interface IMaintenanceService {

    void createMaintenance(Maintenance maintenance, Car car) throws DataStoreException;
    void deleteMaintenance(Maintenance maintenance) throws DataStoreException;

}
