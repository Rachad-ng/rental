package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Maintenance;
import com.negra.location.model.Car;
import com.negra.location.repository.EntretienRepository;
import com.negra.location.service.interfaces.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class MaintenanceService implements IMaintenanceService {

    @Autowired
    private EntretienRepository entretienRepository;

    public void createMaintenance(Maintenance maintenance, Car car){
        car.addMaintenance(maintenance);
        try{
            entretienRepository.save(maintenance);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    public void deleteMaintenance(Maintenance maintenance){
        maintenance.getCar().removeMaintenance(maintenance);
        try{
            entretienRepository.delete(maintenance);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}
