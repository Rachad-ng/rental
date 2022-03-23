package com.negra.location.service.implementations;

import com.negra.location.dto.*;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.*;
import com.negra.location.repository.AgentRepository;
import com.negra.location.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.negra.location.utility.DataUtility.NUMBER_PLACES_MAX;
import static com.negra.location.utility.ErrorMessage.*;

@Service
@Transactional
public class AgentService implements IAgentService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IMarkService markService;
    @Autowired
    private IFuelService fuelService;
    @Autowired
    private ICategorieService categoryService;

    public void createAgent(AgentRegistrationDto agentRegistrationDto)
    {
        userService.isUserExists(agentRegistrationDto.getEmail());
        Agent agent = new Agent();
        Address address = new Address();

        MapperService.agentRegitrationDtoToAgentAndAddress(agentRegistrationDto, agent, address);
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        try{
            agentRepository.save(agent);
            addressService.createAddress(address, agent);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    // Suppression d'agent après la suppression de l'adresse associe s'elle existe
    public void deleteAgent(Agent agent){
        Address address = agent.getAddress();
        try {
            if(address != null)
                addressService.deleteAddress(address);

            agentRepository.delete(agent);
        }catch(Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    // Recueration des donnée nécessaire pour l'ajout d'une voiture
    public void initialisationCarCreationFrom(org.springframework.ui.Model model) {

        List<Mark> marks = markService.findAll();
        Mark mark = marks.get(0);
        Set<Model> modelSet = mark.getModelSet();
        List<Model> modelList = new ArrayList<>(modelSet);

        // Recuperation des modelDto de la premier marque pour remplir le select du formulaire
        List<Model> models = new ArrayList<>(marks.get(0).getModelSet());
        List<ModelDto> modelDtos = new ArrayList<>();
        MapperService.modelsToModelDtos(models, modelDtos);

        List<CategorieDto> categorieDtos = categoryService.findAllDtos();
        List<FuelDto> fuelDtos = fuelService.findAllDtos();

        // Preparation de Dto pour le transfert et validation des données
        CarCreationDto carCreationDto = new CarCreationDto();
        carCreationDto.setMarks(marks);
        carCreationDto.setModelDtos(modelDtos);
        carCreationDto.setFuelDtos(fuelDtos);
        carCreationDto.setCategorieDtos(categorieDtos);

        model.addAttribute("voitureCreationDto", carCreationDto);
        model.addAttribute("nbMaxPlaces", NUMBER_PLACES_MAX);
    }


    public void recuperationInputData(CarCreationDto carCreationDto, org.springframework.ui.Model model) {

        // Recuperation des modeles de l'état precedente du formulaire
        Mark mark = markService.findById(carCreationDto.getIdMark());
        List<Model> models = new ArrayList<>(mark.getModelSet());
        MapperService.modelsToModelDtos(models, carCreationDto.getModelDtos());

        // On doit récupérer les données non-valider (Puisqu'il sont pas garder par le validator)
        List<Mark> marks = markService.findAll();
        List<FuelDto> fuelDtos = fuelService.findAllDtos();
        List<CategorieDto> categorieDtos = categoryService.findAllDtos();

        carCreationDto.setMarks(marks);
        carCreationDto.setFuelDtos(fuelDtos);
        carCreationDto.setCategorieDtos(categorieDtos);

        model.addAttribute("voitureCreationDto", carCreationDto);
        model.addAttribute("errorMessage", ERROR_CAR_CREATION_MAPPING);
        model.addAttribute("nbMaxPlaces", NUMBER_PLACES_MAX);
    }

}
