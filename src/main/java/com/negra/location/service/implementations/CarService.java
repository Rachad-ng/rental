package com.negra.location.service.implementations;

import com.negra.location.dto.*;
import com.negra.location.service.interfaces.ICarService;
import com.negra.location.exception.*;
import com.negra.location.model.*;
import com.negra.location.repository.CarRepository;
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
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ICostService costService;
    @Autowired
    private IMaintenanceService maintenanceService;
    @Autowired
    private IBookingService reservationService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IFuelService fuelService;
    @Autowired
    private IModelService modelService;
    @Autowired
    private ICategoryService categoryService;

    @Override
    public Map<String, Object> getHomePageCars() {
        Map<String, Object> data = new HashMap<>();

        try{
            Page<Car> newCarsPage = carRepository.findNewCars(PageRequest.of(0,5));
            List<Car> newCars = newCarsPage.getContent();
            List<CarAndModelWithImageDto> newCarAndModelWithImageDtos = new ArrayList<>();
            MapperService.carsToCarAndModelWithImageDtos(newCars, newCarAndModelWithImageDtos);

            Page<Car> bestOffreCarsPage = carRepository.findHotOffers(PageRequest.of(0, 4));
            List<Car> bestOffreCars = bestOffreCarsPage.getContent();
            List<ListingCarDto> bestOffreCarDtos = new ArrayList<>();
            MapperService.carsToListingCarDtos(bestOffreCars, bestOffreCarDtos);

            data.put("newCarAndModelWithImageDtos", newCarAndModelWithImageDtos);
            data.put("bestOffreCarDtos", bestOffreCarDtos);

            return data;
        }catch(Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    public void createCar(CarCreationDto carCreationDto) {

        // Verification d'existance des objets, sinn on lève une exception au controlleur
        Model model = modelService.findById(carCreationDto.getIdModel());
        Fuel fuel = fuelService.findById(carCreationDto.getIdFuel());
        Category category = categoryService.findById(carCreationDto.getIdCategory());

        // Verification d'unicité des matricules (La voiture de doit pas exister)
        Car car = findByRegistrationNumber(carCreationDto.getRegistrationNumber());

        // Recuperation de l'agent (Current user)
        String username = userService.getCurrentUsername();
        Agent agent = (Agent) userService.findByUsername(username);

        car = new Car();
        MapperService.carCreationDtoToCar(carCreationDto, car);

        insertCar(car, model, fuel, agent, category);
    }

    public void insertCar(Car car, Model model, Fuel fuel, Agent agent, Category category) {
        model.addCar(car);
        fuel.addCar(car);
        agent.addCar(car);
        category.addCar(car);
        try{
            carRepository.save(car);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    public Car findByRegistrationNumber(String matricule) {
        Optional<Car> optinalVoiture = carRepository.findByRegistrationNumber(matricule);
        if(optinalVoiture.isPresent())
            throw new AlreadyExistsException(ERROR_CAR_MATRICLE_ALREADY_EXISTS);
        else
            return null;
    }

    public Map<String, Object> findAll(int page, int size){
        try{
            Page<Car> carPages = carRepository.findAllOrdredByIdDesc(PageRequest.of(page, size));
            int numberOfPages = carPages.getTotalPages();
            long numberOfCars = carPages.getTotalElements();
            List<Car> cars = carPages.getContent();

            // Mapper le resultat dans une list ListingCarDto
            List<ListingCarDto> listingCarDtos = new ArrayList<>();
            MapperService.carsToListingCarDtos(cars, listingCarDtos);

            // Création d'une Map, pour passer les données nécessaire à la couche de présentation
            Map<String, Object> data = new HashMap<>();
            data.put("listingCarDtos", listingCarDtos);
            data.put("numberOfPages", numberOfPages);
            data.put("numberOfCars", numberOfCars);

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
            costService.deleteCost(frai);

        // Supression des entretiens assicies
        Set<Maintenance> maintenances = car.getMaintenanceSet();
        for (Maintenance maintenance : maintenances)
            maintenanceService.deleteMaintenance(maintenance);

        // Suppression des reservation
        Set<Reservation> reservations = car.getReservationSet();
        for (Reservation reservation: reservations)
            reservationService.deleteReservation(reservation);

        try{
            carRepository.delete(car);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    public Map<String, Object> getListingDetailsCarDtoAndSimilarListingCarDtos(long id){
        Map<String, Object> data = new HashMap<>();

        ListingDetailsCarDto listingDetailsCarDto = new ListingDetailsCarDto();
        Car car = carRepository.getById(id);
        MapperService.carToListingDetailsCarDto(car, listingDetailsCarDto);

        // Recuperation des voitures similaires
        Page<Car> smilarCarsPage = carRepository.findSimilarCars(car.getCategory().getId(), car.getFuel().getId(), car.getPricePerDay() / 2, car.getPricePerDay() * 2, car.getId(), PageRequest.of(0,3));
        List<Car> smilarCars = smilarCarsPage.getContent();
        List<ListingCarDto> similarListingCarDtos = new ArrayList<>();
        MapperService.carsToListingCarDtos(smilarCars, similarListingCarDtos);

        data.put("listingDetailsCarDto", listingDetailsCarDto);
        data.put("similarListingCarDtos", similarListingCarDtos);

        return data;
    }

}
