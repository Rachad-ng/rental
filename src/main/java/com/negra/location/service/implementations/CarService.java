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
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private IMarkService markService;
    @Autowired
    private IBookingService bookingService;

    @Override
    public Map<String, Object> getHomePageListings() {
        Map<String, Object> data = new HashMap<>();

        try{
            Page<Car> mostPopularListingsPage = carRepository.findMostPopular(PageRequest.of(0,5));
            List<Car> mostPopularListings = mostPopularListingsPage.getContent();

        /*
            List<Car> cars = carRepository.findAll();
            Collections.sort(cars, new Comparator<Car>() {
                @Override
                public int compare(Car c1, Car c2) {
                    return c2.getVisitSet().size() - c1.getVisitSet().size();
                }
            });
            List<Car> mostPopularListings = cars.subList(0, 5);
        */

            List<CarAndModelWithImageDto> mostPopularCarAndModelWithImageDtos = new ArrayList<>();
            MapperService.carsToCarAndModelWithImageDtos(mostPopularListings, mostPopularCarAndModelWithImageDtos);

            Page<Car> bestListingCarsPage = carRepository.findHotListingCars(PageRequest.of(0, 4));
            List<Car> bestListingCars = bestListingCarsPage.getContent();
            List<ListingDto> hotListingDtos = new ArrayList<>();
            MapperService.carsToListingDtos(bestListingCars, hotListingDtos);

            data.put("mostPopularCarAndModelWithImageDtos", mostPopularCarAndModelWithImageDtos);
            data.put("hotListingDtos", hotListingDtos);

            return data;
        }catch(Exception e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    @Override
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

    @Override
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

    @Override
    public Car findByRegistrationNumber(String matricule) {
        Optional<Car> optinalVoiture = carRepository.findByRegistrationNumber(matricule);
        if(optinalVoiture.isPresent())
            throw new AlreadyExistsException(ERROR_CAR_REGISTRATION_NUMBER_ALREADY_EXISTS);
        else
            return null;
    }

    @Override
    public Map<String, Object> findAll(int page, int size){
        try{
            Page<Car> listingsPage = carRepository.findAllOrdredByIdDesc(PageRequest.of(page, size));
            int numberOfPages = listingsPage.getTotalPages();
            long numberOfListings = listingsPage.getTotalElements();
            List<Car> cars = listingsPage.getContent();

            // Mapper le resultat dans une list ListingCarDto
            List<ListingDto> listingDtos = new ArrayList<>();
            MapperService.carsToListingDtos(cars, listingDtos);

            // Création d'une Map, pour passer les données nécessaire à la couche de présentation
            Map<String, Object> data = new HashMap<>();
            data.put("listingDtos", listingDtos);
            data.put("numberOfPages", numberOfPages);
            data.put("numberOfListings", numberOfListings);

            return data;
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
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
        Set<Booking> bookings = car.getBookingSet();
        for (Booking booking : bookings)
            reservationService.deleteReservation(booking);

        try{
            carRepository.delete(car);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public Map<String, Object> getListingDetailsDtoAndSimilarListingDtos(long id){
        Map<String, Object> data = new HashMap<>();

        ListingDetailsDto listingDetailsDto = new ListingDetailsDto();
        Car car = carRepository.getById(id);
        MapperService.carToListingDetailsCarDto(car, listingDetailsDto);

        // Recuperation des voitures similaires
        Page<Car> smilarCarsPage = carRepository.findSimilarCars(car.getCategory().getId(), car.getFuel().getId(), car.getPricePerDay() / 2, car.getPricePerDay() * 2, car.getId(), PageRequest.of(0,3));
        List<Car> smilarCars = smilarCarsPage.getContent();
        List<ListingDto> similarListingDtos = new ArrayList<>();
        MapperService.carsToListingDtos(smilarCars, similarListingDtos);

        data.put("listingDetailsDto", listingDetailsDto);
        data.put("similarListingDtos", similarListingDtos);

        return data;
    }

    @Override
    public Car findById(Long id) throws DataNotFoundException {
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent())
            return car.get();
        else
            throw new DataNotFoundException(ERROR_CAR_NOT_FOUND);
    }

    @Override
    public List<Long> getNotReservedCarIds() {
        return carRepository.getNotReservedCars();
    }

    @Override
    public List<Car> findListingsWithoutDateConstraint(String markLibelle, String modelLibelle, String town) {
        return carRepository.findCarsWithoutDateConstraint(markLibelle, modelLibelle, town);
    }

    @Override
    public List<Car> getAvailableCars(HomeSearchCarDto homeSearchCarDto) throws DateException {

        int idMark = homeSearchCarDto.getIdMark();
        int idModel = homeSearchCarDto.getIdModel();
        String town = homeSearchCarDto.getTown();
        LocalDate startDate = homeSearchCarDto.getStartDate();
        LocalDate backDate = homeSearchCarDto.getBackDate();

        Mark mark;
        Model model;
        String markLibelle = "%", modelLibelle = "%";

        List<Car> carList;

        if (idModel >= 1) {
            try {
                model = modelService.findById(idModel);
                modelLibelle = model.getLibelle();
            } catch (DataNotFoundException e) {
                // Si le model n'existe pas, on selectionne tous les modeles
                if (idMark >= 1) {
                    try {
                        mark = markService.findById(idMark);
                        markLibelle = mark.getLibelle();
                    } catch (DataNotFoundException e1) {
                        // Si la marque n'existe pas, on selectionne tous les marques
                    }
                }
            }
        } else {
            // Si le model n'existe pas, on teste si l'utilisateur est intérésseé que par la marque
            if (idMark >= 1) {
                try {
                    mark = markService.findById(idMark);
                    markLibelle = mark.getLibelle();
                } catch (DataNotFoundException e1) {
                    // Si la marque n'existe pas, on selectionne tous les marques
                }
            }
        }

        if ("".equals(town))
            town = "%";

        carList = findListingsWithoutDateConstraint(markLibelle, modelLibelle, town);

        // Manage Booking date constraints
        if (startDate != null) {
            if(startDate.isAfter(LocalDate.now())) {

                // On doit éliminer les voitures déja résérvées
                List<Long> reservedCarIds;
                List<Long> notReservedCarIds = getNotReservedCarIds();

                if (backDate != null) {
                    if (backDate.isAfter(startDate)) {
                        // La plage de date est précisée

                        // Selectionner les voitures dont leurs reservations se terminent avant , ou commencent après la réservation souhaitée.
                        reservedCarIds = bookingService.getReservedCarIds(startDate, backDate);

                        // Selectionner les voitures non-réservées ou bien celles qui satisfait la contraites des réservations en cours
                        carList = carList.stream().
                                filter(c -> notReservedCarIds.contains(c.getId()) || reservedCarIds.contains(c.getId())).collect(Collectors.toList());
                    } else
                        throw new DateException(ERROR_RESERVATION_END_DATE_INVALID);
                } else {
                    // L'utilisateur n'a spécifié que la date de départ

                    // Selectionner les voitures dont les période de réservations contient la date souhaitée.
                    reservedCarIds = bookingService.getReservedCarIds(startDate);

                    // Selectionner les voitures non-réservées ou bien celles qui satisfait la contraites des réservations en cours
                    carList = carList.stream().
                            filter(c -> notReservedCarIds.contains(c.getId()) || !reservedCarIds.contains(c.getId())).collect(Collectors.toList());
                }
            }else
                throw new DateException(ERROR_RESERVATION_START_DATE_INVALID);
        }

        return carList;
    }

}
