package com.negra.location.service.implementations;

import com.negra.location.dto.*;
import com.negra.location.model.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public static FuelDto carburantToCarburantDto(Fuel fuel, FuelDto fuelDto){
        fuelDto.setId(fuel.getId());
        fuelDto.setLibelle(fuel.getLibelle());
        return fuelDto;
    }

    public static List<FuelDto> carburantsListToDtos(List<Fuel> fuels, List<FuelDto> fuelDtos){
        FuelDto fuelDto;
        for (Fuel fuel : fuels){
            fuelDto = new FuelDto();
            fuelDto.setId(fuel.getId());
            fuelDto.setLibelle(fuel.getLibelle());
            fuelDtos.add(fuelDto);
        }
        return fuelDtos;
    }

    public static void voitureCreationDtoToVoiture(CarCreationDto carCreationDto, Car car){

        car.setAirConditioning(carCreationDto.isClimatisation());
        car.setColor(carCreationDto.getCouleur());
        car.setDateCirculation(carCreationDto.getDateMiseCirculation());
        car.setMileage(carCreationDto.getKilometrage());
        car.setRegistrationNumber(carCreationDto.getMatricule());
        car.setNumberPlaces(carCreationDto.getNombrePlaces());
        car.setNumberDoors(carCreationDto.getNombrePortes());
        car.setPricePerDay(carCreationDto.getPrixJour());
        car.setAndroidAvailable(carCreationDto.isPosteAndroid());
        car.setAutoTransmission(carCreationDto.isBoiteAuto());

        car.setAvailable(true);
    }

    public static void modelsToModelDtos(List<Model> models, List<ModelDto> modelDtos){
        for (Model model: models)
            modelDtos.add(new ModelDto(model.getId(), model.getLibelle()));
    }

    public static void voitureToVoitureDto(Car car, VoitureDto voitureDto){
        voitureDto.setBoiteAuto(car.isAutoTransmission());
        voitureDto.setId(car.getId());
        voitureDto.setPrixJour(car.getPricePerDay());
        voitureDto.setDateMiseCirculation(car.getDateCirculation());
        voitureDto.setCategorieDto(categorieToCategorieDto(car.getCategory(), new CategorieDto()));
        voitureDto.setModelAndMarkDto(modelToModelAndMarkDto(car.getModel(), new ModelAndMarkDto()));
        voitureDto.setFuelDto(carburantToCarburantDto(car.getFuel(), new FuelDto()));
    }

    public static CategorieDto categorieToCategorieDto(Category category, CategorieDto categorieDto){
        categorieDto.setId(category.getId());
        categorieDto.setLibelle(category.getLibelle());
        return categorieDto;
    }

    public static List<CategorieDto> categoriesToCategorieDtos(List<Category> categories, List<CategorieDto> categorieDtos){
        CategorieDto categorieDto;
        for (Category category : categories) {
            categorieDto = new CategorieDto();
            categorieDto.setId(category.getId());
            categorieDto.setLibelle(category.getLibelle());
            categorieDtos.add(categorieDto);
        }
        return categorieDtos;
    }

    public static ModelAndMarkDto modelToModelAndMarkDto(Model model, ModelAndMarkDto modelAndMarkDto){
        modelAndMarkDto.setLibelle(model.getLibelle());
        modelAndMarkDto.setMarqueDto(markToMarkDto(model.getMark(), new MarqueDto()));
        return modelAndMarkDto;
    }

    public static MarqueDto markToMarkDto(Mark mark, MarqueDto marqueDto){
        marqueDto.setId(mark.getId());
        marqueDto.setLibelle(mark.getLibelle());
        return marqueDto;
    }

    public static void clientRegitrationDtoToClient(ClientRegistrationDto clientRegistrationDto, Client client){
        client.setLastName(clientRegistrationDto.getLastname());
        client.setFirstname(clientRegistrationDto.getFirstname());
        client.setEmail(clientRegistrationDto.getEmail());
        client.setPassword(clientRegistrationDto.getPassword());
        client.setTel(clientRegistrationDto.getTel());
        client.setRole(clientRegistrationDto.getRole());
    }

}
