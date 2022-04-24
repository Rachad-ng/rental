package com.negra.location.service.implementations;

import com.negra.location.exception.*;
import com.negra.location.model.Car;
import com.negra.location.model.Client;
import com.negra.location.model.User;
import com.negra.location.model.Visit;
import com.negra.location.repository.VisitRepository;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.service.interfaces.IUserService;
import com.negra.location.service.interfaces.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.negra.location.utility.ErrorMessage.ERROR_VISIT_NOT_COUNTED;

@Service
public class VisitService implements IVisitService {

    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private ICarService carService;
    @Autowired
    private IUserService userService;

    @Override
    public void initializeVisit(Long id) {
         String username;
         User user;
         Client client = null;

        Car car = carService.findById(id);
        // Vérification qu'il ne s'agit pas d'une visite de propriétaire de la voiture
        try{
            username = userService.getCurrentUsername();
            if(!car.getAgent().getEmail().equals(username)){
                user = userService.findByUsername(username);
                client = (Client) user;
            }else
                throw new VisitNotCountedException(ERROR_VISIT_NOT_COUNTED);

        }catch(CurrentUserNotFoundException | UserNotFoundException | ClassCastException e){
            // La reference au utilisateur reste null (Anonymous User)
        }

        Visit visit = new Visit();
        visit.setDateTime(LocalDateTime.now());
    //    car.addVisit(visit);
        visit.setCar(car);

        if(client != null)
            client.addVisit(visit);

        visitRepository.save(visit);
    }

    @Override
    public int getCarNumberOfVisits(Long idCar) {
        return visitRepository.getCarNumberOfVisists(idCar);
    }

    @Override
    public LocalDateTime getCarLastVisitDate(Long idCar) {
        return visitRepository.getCarLastVisistDate(idCar);
    }

}
