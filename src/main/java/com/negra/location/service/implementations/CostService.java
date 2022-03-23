package com.negra.location.service.implementations;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Cost;
import com.negra.location.model.Car;
import com.negra.location.repository.FraisRepository;
import com.negra.location.service.interfaces.ICostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.negra.location.utility.ErrorMessage.ERROR_DATA;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA_STORING;

@Service
@Transactional
public class CostService implements ICostService {

    @Autowired
    private FraisRepository fraisRepository;

    public void createCost(Cost cost, Car car){
        car.addCost(cost);
        try {
            fraisRepository.save(cost);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    public void deleteCost(Cost cost){
        cost.getCar().removeCost(cost);
        try{
            fraisRepository.delete(cost);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }
}
