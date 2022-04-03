package com.negra.location.service.implementations;

import com.negra.location.dto.*;
import com.negra.location.model.*;
import com.negra.location.model.Mark;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MapperService {

    public static void agentRegitrationDtoToAgentAndAddress(AgentRegistrationDto agentRegistrationDto, Agent agent, Address address){
        agent.setLastName(agentRegistrationDto.getLastname());
        agent.setFirstname(agentRegistrationDto.getFirstname());
        agent.setEmail(agentRegistrationDto.getEmail());
        agent.setPassword(agentRegistrationDto.getPassword());
        agent.setTel(agentRegistrationDto.getTel());
        agent.setRsAgence(agentRegistrationDto.getRsAgence());
        agent.setRole(agentRegistrationDto.getRole());

        address.setNumber(agentRegistrationDto.getAddressDto().getNumber());
        address.setStreet(agentRegistrationDto.getAddressDto().getStreet());
        address.setDistrict(agentRegistrationDto.getAddressDto().getDistrict());
        address.setTown(agentRegistrationDto.getAddressDto().getTown());
        address.setCountry(agentRegistrationDto.getAddressDto().getCountry());
    }

    public static void carsToCarAndModelWithImageDtos(List<Car> cars, List<CarAndModelWithImageDto> carAndModelWithImageDtos){
        for (Car car: cars)
            carAndModelWithImageDtos.add(new CarAndModelWithImageDto(car.getId(),
                    new ModelWithImageDto(car.getModel().getId(),
                            car.getModel().getLibelle(),
                            car.getModel().getImage(),
                            new MarkDto(car.getModel().getMark().getId(),
                                    car.getModel().getMark().getLibelle())),
                    car.getPricePerDay(), car.isAutoTransmission()));
    }

    public static List<FuelDto> fuelsToFuelDtos(List<Fuel> fuels, List<FuelDto> fuelDtos){
        FuelDto fuelDto;
        for (Fuel fuel : fuels){
            fuelDto = new FuelDto();
            fuelDto.setId(fuel.getId());
            fuelDto.setLibelle(fuel.getLibelle());
            fuelDtos.add(fuelDto);
        }
        return fuelDtos;
    }

    public static void carCreationDtoToCar(CarCreationDto carCreationDto, Car car){

        car.setAirConditioning(carCreationDto.isAirConditioning());
        car.setColor(carCreationDto.getColor());
        car.setDateCirculation(carCreationDto.getDateCirculation());
        car.setMileage(carCreationDto.getMileage());
        car.setRegistrationNumber(carCreationDto.getRegistrationNumber());
        car.setNumberOfPlaces(carCreationDto.getNumberPlaces());
        car.setNumberOfDoors(carCreationDto.getNumberDoors());
        car.setPricePerDay(carCreationDto.getPricePerDay());
        car.setAndroidAvailable(carCreationDto.isAndroidAvailable());
        car.setAutoTransmission(carCreationDto.isAutoTransmission());
        car.setTintedGlass(carCreationDto.isTintedGlass());
        car.setSunroof(carCreationDto.isSunroof());
        car.setChildSeat(carCreationDto.isChildSeat());

        car.setAvailable(true);
    }

    public static void modelsToModelDtos(List<Model> models, List<ModelDto> modelDtos){
        for (Model model: models)
            modelDtos.add(new ModelDto(model.getId(), model.getLibelle()));
    }

    public static void carsToListingDtos(List<Car> cars, List<ListingDto> listingDtos){
        ListingDto listingDto;
        for (Car car : cars) {
            listingDto = new ListingDto();
            carToListingDto(car, listingDto);
            listingDtos.add(listingDto);
        }
    }

    public static void carToListingDto(Car car, ListingDto listingDto){

        listingDto.setId(car.getId());
        listingDto.setPricePerDay(car.getPricePerDay());
        listingDto.setDateCirculation(car.getDateCirculation());
        listingDto.setAutoTransmission(car.isAutoTransmission());
        listingDto.setListingFuelDto(new ListingFuelDto(car.getFuel().getLibelle()));
        listingDto.setListingCategoryDto(new ListingCategoryDto(car.getCategory().getLibelle()));

        listingDto.setListingModelDto(
                new ListingModelDto(
                        car.getModel().getLibelle(), car.getModel().getImage(),
                        new ListingMarkDto(car.getModel().getMark().getLibelle())
                ));

        listingDto.setListingAgenceWithTownDto(
                new ListingAgenceWithTownDto(
                        new AddressTownDto(car.getAgent().getAddress().getTown()),
                        car.getAgent().getRsAgence()
                ));
    }

    public static List<CategoryDto> categoriesToCategoryDtos(List<Category> categories, List<CategoryDto> categoryDtos){
        CategoryDto categoryDto;
        for (Category category : categories) {
            categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setLibelle(category.getLibelle());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    public static void marksToMarkWithModelDtos(List<Mark> marks, List<MarkWithModelDto> markWithModelDtos){
        MarkWithModelDto markWithModelDto;
        for (Mark mark: marks) {
            markWithModelDto = new MarkWithModelDto();
            markToMarkWithModelDto(mark, markWithModelDto);
            markWithModelDtos.add(markWithModelDto);
        }
    }

    public static void markToMarkWithModelDto(Mark mark, MarkWithModelDto markWithModelDto){
        markWithModelDto.setId(mark.getId());
        markWithModelDto.setLibelle(mark.getLibelle());
        List<Model> modelList = new ArrayList<>(mark.getModelSet()).stream().sorted(Comparator.comparing(Model::getLibelle)).collect(Collectors.toList());
        for (Model model: modelList)
            markWithModelDto.getModelDtos().add(new ModelDto(model.getId(), model.getLibelle()));
    }

    public static void clientRegitrationDtoToClient(ClientRegistrationDto clientRegistrationDto, Client client){
        client.setLastName(clientRegistrationDto.getLastname());
        client.setFirstname(clientRegistrationDto.getFirstname());
        client.setEmail(clientRegistrationDto.getEmail());
        client.setPassword(clientRegistrationDto.getPassword());
        client.setTel(clientRegistrationDto.getTel());
        client.setRole(clientRegistrationDto.getRole());
    }

    public static void carToListingDetailsCarDto(Car car, ListingDetailsDto listingDetailsDto){
        listingDetailsDto.setId(car.getId());
        listingDetailsDto.setPricePerDay(car.getPricePerDay());
        listingDetailsDto.setAutoTransmission(car.isAutoTransmission());
        listingDetailsDto.setColor(car.getColor());
        listingDetailsDto.setAndroidAvailable(car.isAndroidAvailable());
        listingDetailsDto.setCirculationYear(car.getDateCirculation().getYear());
        listingDetailsDto.setNumberOfPlaces(car.getNumberOfPlaces());
        listingDetailsDto.setNumberOfDoors(car.getNumberOfDoors());
        listingDetailsDto.setAirConditioning(car.isAirConditioning());
        listingDetailsDto.setTintedGlass(car.isTintedGlass());
        listingDetailsDto.setChildSeat(car.isChildSeat());
        listingDetailsDto.setSunroof(car.isSunroof());

        listingDetailsDto.setListingCategoryDto(new ListingCategoryDto(car.getCategory().getLibelle()));
        listingDetailsDto.setListingFuelDto(new ListingFuelDto(car.getFuel().getLibelle()));
        listingDetailsDto.setListingModelDto(new ListingModelDto(car.getModel().getLibelle(), car.getModel().getImage(), new ListingMarkDto(car.getModel().getMark().getLibelle())));
        listingDetailsDto.setAgentDto(new AgentDto(car.getAgent().getEmail(), car.getAgent().getTel(), car.getAgent().getTel().substring(0,6) + "....", new AddressDto(car.getAgent().getAddress()), car.getAgent().getRsAgence(), car.getAgent().getNotoriety()));
    }

}
