package com.negra.location.service.implementations;

import com.negra.location.service.interfaces.ICarService;
import com.negra.location.dto.CarCreationDto;
import com.negra.location.dto.VoitureDto;
import com.negra.location.exception.*;
import com.negra.location.model.*;
import com.negra.location.repository.VoitureRepository;
import com.negra.location.service.interfaces.IMaintenanceService;
import com.negra.location.service.interfaces.ICostService;
import com.negra.location.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.negra.location.utility.ErrorMessage.*;

@Service
@Transactional
public class VoitureService implements ICarService {

    @Autowired
    private VoitureRepository voitureRepository;
    @Autowired
    private ICostService fraisService;
    @Autowired
    private IMaintenanceService entretienService;
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IUserService utilisateurService;
    @Autowired
    private IFuelService carburantService;
    @Autowired
    private IModelService modelService;
    @Autowired
    private ICategorieService categorieService;

    public void createCar(CarCreationDto carCreationDto) {

        // Verification d'existance des objets, sinn on lève une exception au controlleur
        Model model = modelService.findById(carCreationDto.getIdModel());
        Fuel fuel = carburantService.findById(carCreationDto.getIdCarburant());
        Category category = categorieService.findById(carCreationDto.getIdCategorie());

        // Verification d'unicité des matricules (La voiture de doit pas exister)
        Car car = findByRegistrationNumber(carCreationDto.getMatricule());

        // Recuperation de l'agent (Current user)
        String username = utilisateurService.getCurrentUsername();
        Agent agent = (Agent) utilisateurService.findByUsername(username);

        car = new Car();
        MapperService.voitureCreationDtoToVoiture(carCreationDto, car);

        insertCar(car, model, fuel, agent, category);
    }

    public void insertCar(Car car, Model model, Fuel fuel, Agent agent, Category category) {
        model.addCar(car);
        fuel.addCar(car);
        agent.addCar(car);
        category.addCar(car);
        try{
            voitureRepository.save(car);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    public Car findByRegistrationNumber(String matricule) {
        Optional<Car> optinalVoiture = voitureRepository.findByRegistrationNumber(matricule);
        if(optinalVoiture.isPresent())
            throw new AlreadyExistsException(ERROR_CAR_MATRICLE_ALREADY_EXISTS);
        else
            return null;
    }

    public Map<String, Object> findAll(int page, int size){
        try{
            Page<Car> voiturePages = voitureRepository.findAll(PageRequest.of(page, size));
            int nombrePage = voiturePages.getTotalPages();
            long nombreCars = voiturePages.getTotalElements();
            List<Car> cars = voiturePages.getContent();
            List<VoitureDto> voitureDtos = new ArrayList<>();
            VoitureDto voitureDto;
            for (Car car : cars) {
                voitureDto = new VoitureDto();
                MapperService.voitureToVoitureDto(car, voitureDto);
                voitureDtos.add(voitureDto);
            }
            Map<String, Object> data = new HashMap<>();
            data.put("voitureDtos", voitureDtos);
            data.put("nombrePages", nombrePage);
            data.put("nombreCars", nombreCars);
            return data;
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }


    public void deleteCar(Car car){

        car.getModel().removeCar(car);
        car.getAgent().removeCar(car);

        // Suppression des frais associes
        Set<Cost> frais = car.getCostSet();
        for (Cost frai: frais)
            fraisService.deleteCost(frai);

        // Supression des entretiens assicies
        Set<Maintenance> maintenances = car.getMaintenanceSet();
        for (Maintenance maintenance : maintenances)
            entretienService.deleteMaintenance(maintenance);

        // Suppression des reservation
        Set<Reservation> reservations = car.getReservationSet();
        for (Reservation reservation: reservations)
            reservationService.deleteReservation(reservation);

        try{
            voitureRepository.delete(car);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}
