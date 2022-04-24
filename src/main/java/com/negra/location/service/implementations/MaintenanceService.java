package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.exception.OilChangeMaxMileageException;
import com.negra.location.model.Maintenance;
import com.negra.location.model.Car;
import com.negra.location.model.OilChange;
import com.negra.location.repository.MaintenanceRepository;
import com.negra.location.service.interfaces.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.negra.location.utility.ErrorMessage.*;

@Service
@Transactional
public class MaintenanceService implements IMaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Override
    public void createMaintenance(Maintenance maintenance, Car car) throws OilChangeMaxMileageException, DataStoreException {
        car.addMaintenance(maintenance);
        try {
            verifyMaxMileage(maintenance, car);
            maintenanceRepository.save(maintenance);
        }catch (OilChangeMaxMileageException e){
            throw new OilChangeMaxMileageException(e.getMessage());
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    @Override
    public void deleteMaintenance(Maintenance maintenance){
        maintenance.getCar().removeMaintenance(maintenance);
        try{
            maintenanceRepository.delete(maintenance);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public List<Maintenance> findByCarId(Long idCar) {
        return maintenanceRepository.findByCarId(idCar);
    }

    @Override
    public void verifyMaxMileage(Maintenance maintenance, Car car) throws OilChangeMaxMileageException {

        if(maintenance instanceof OilChange){
            OilChange newOilChange = (OilChange) maintenance;

            List<Maintenance> maintenances = findByCarId(car.getId());

            if(maintenances.size() > 0){
                List<Maintenance> oilChanges = maintenances.stream().filter(m -> m instanceof OilChange).collect(Collectors.toList());
                if(oilChanges.size() > 0){
                    OilChange lastOilChange = (OilChange) oilChanges.stream()
                            .sorted(Comparator.comparing(Maintenance::getId).reversed())
                            .collect(Collectors.toList()).get(0);

                    if(lastOilChange.getMaxMileage() >= newOilChange.getMaxMileage())
                        throw new OilChangeMaxMileageException(ERROR_OIL_CHANGE_MILEAGE_MAX_ALREADY_EXISIT);
                }
            }
        }
    }

}
