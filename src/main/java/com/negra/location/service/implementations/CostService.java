package com.negra.location.service.implementations;

import com.negra.location.exception.CostDateException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.*;
import com.negra.location.repository.CostRepository;
import com.negra.location.service.interfaces.ICostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.negra.location.utility.ErrorMessage.*;

@Service
@Transactional
public class CostService implements ICostService {

    @Autowired
    private CostRepository costRepository;

    @Override
    public void createCost(Cost cost, Car car){
        car.addCost(cost);
        try {
            verifyCostDate(cost, car.getId());
            costRepository.save(cost);
        }catch (CostDateException e){
            throw new CostDateException(e.getMessage());
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    @Override
    public void verifyCostDate(Cost cost, Long idCar) throws CostDateException {

        List<Cost> costs = costRepository.findByCarId(idCar);

        List<Cost> assurances = costs.stream().filter(c -> c instanceof Assurance).collect(Collectors.toList());
        List<Cost> technicalVisits = costs.stream().filter(c -> c instanceof TechnicalVisit).collect(Collectors.toList());
        List<Cost> carStickers = costs.stream().filter(c -> c instanceof CarSticker).collect(Collectors.toList());

        if(costs.size() > 0){

            int numbreOfCosts;

            // Si la charge est une assurance , et la voiture à déja des assurances , on doit vérifier que la date est ultérieure
            if(cost instanceof Assurance){
                if(assurances.size() > 0 ){
                    numbreOfCosts = assurances.stream().filter(a -> a.getDueDate().isAfter(cost.getDueDate())).collect(Collectors.toList()).size();

                    if(numbreOfCosts > 0)
                        throw new CostDateException(ERROR_COST_END_DATE_IS_BEFORE);
                }
            }else if(cost instanceof TechnicalVisit){
                if(technicalVisits.size() > 0 ){
                    numbreOfCosts = technicalVisits.stream().filter(a -> a.getDueDate().isAfter(cost.getDueDate())).collect(Collectors.toList()).size();

                    if(numbreOfCosts > 0)
                        throw new CostDateException(ERROR_COST_END_DATE_IS_BEFORE);
                }
            }else if(cost instanceof CarSticker){
                if(carStickers.size() > 0 ){
                    numbreOfCosts = carStickers.stream().filter(a -> a.getDueDate().isAfter(cost.getDueDate())).collect(Collectors.toList()).size();

                    if(numbreOfCosts > 0)
                        throw new CostDateException(ERROR_COST_END_DATE_IS_BEFORE);
                }
            }
        }
    }

    @Override
    public void deleteCost(Cost cost){
        cost.getCar().removeCost(cost);
        try{
            costRepository.delete(cost);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public List<Cost> findByCarId(Long idCar) {
        return costRepository.findByCarId(idCar);
    }

}
