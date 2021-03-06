package com.negra.location.service.implementations;

import com.negra.location.dto.*;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.mapper.AddressDtoMapper;
import com.negra.location.mapper.AgentRegistrationDtoMapper;
import com.negra.location.mapper.MarkWithModelDtoMapper;
import com.negra.location.mapper.ModelDtoMapper;
import com.negra.location.model.*;
import com.negra.location.model.Mark;
import com.negra.location.repository.AgentRepository;
import com.negra.location.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.negra.location.utility.DataUtility.NUMBER_PLACES_MAX;
import static com.negra.location.utility.ErrorMessage.*;

@AllArgsConstructor
@Service
@Transactional
public class AgentService implements IAgentService {

    private PasswordEncoder passwordEncoder;
    private AgentRepository agentRepository;
    private IAddressService addressService;
    private IUserService userService;
    private IMarkService markService;
    private IFuelService fuelService;
    private ICategoryService categoryService;
    private ModelDtoMapper modelDtoMapper;
    private MarkWithModelDtoMapper markWithModelDtoMapper;
    private AddressDtoMapper addressDtoMapper;
    private AgentRegistrationDtoMapper agentRegistrationDtoMapper;

    @Override
    public void createAgent(AgentRegistrationDto agentRegistrationDto) throws DataNotFoundException, AlreadyExistsException, DataStoreException
    {
        // Verification d'unicitée d'email, s'il existe déja en lève AlredyExisitException
        userService.isUserExists(agentRegistrationDto.getEmail());

        Agent agent = agentRegistrationDtoMapper.AgentRegistrationDtoToAgent(agentRegistrationDto);
        Address address = addressDtoMapper.addressDtoToAddress(agentRegistrationDto.getAddressDto());
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));

        try{
            agentRepository.save(agent);
            addressService.createAddress(address, agent);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    // Suppression d'agent après la suppression de l'adresse associe s'elle existe
    @Override
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
    @Override
    public void initialisationCarCreationFrom(org.springframework.ui.Model model) throws
            DataStoreException, IndexOutOfBoundsException {

        List<Mark> marks = markService.findAll();
        List<MarkWithModelDto> markWithModelDtos = markWithModelDtoMapper.markToMarkWithModelDto(marks);

        // Recuperation des modelDto de la premier marque pour remplir le select du formulaire (On les trie pour bien adapter l'affichage)
        List<Model> models = new ArrayList<>(marks.get(0).getModelSet()).stream().sorted(Comparator.comparing(Model::getLibelle)).collect(Collectors.toList());
        List<ModelDto> modelDtos = modelDtoMapper.modelToModelDto(models);

        List<CategoryDto> categoryDtos = categoryService.findAllDtos();
        List<FuelDto> fuelDtos = fuelService.findAllDtos();

        // Preparation de Dto pour le transfert et validation des données
        CarCreationDto carCreationDto = new CarCreationDto();
        carCreationDto.setMarkWithModelDtos(markWithModelDtos);
        carCreationDto.setModelDtos(modelDtos);
        carCreationDto.setFuelDtos(fuelDtos);
        carCreationDto.setCategoryDtos(categoryDtos);

        model.addAttribute("carCreationDto", carCreationDto);
        model.addAttribute("nbMaxPlaces", NUMBER_PLACES_MAX);
    }

    @Override
    public void recuperationInputData(CarCreationDto carCreationDto, org.springframework.ui.Model model) {

        // Recuperation des modeles de l'état precedente du formulaire
        Mark mark = markService.findById(carCreationDto.getIdMark());
        List<Model> models = new ArrayList<>(mark.getModelSet());

        carCreationDto.setModelDtos(modelDtoMapper.modelToModelDto(models));

        // On doit récupérer les données non-valider (Puisqu'il sont pas garder par le validator)
        List<Mark> marks = markService.findAll();
        List<MarkWithModelDto> markWithModelDtos = markWithModelDtoMapper.markToMarkWithModelDto(marks);

        List<FuelDto> fuelDtos = fuelService.findAllDtos();
        List<CategoryDto> categoryDtos = categoryService.findAllDtos();

        carCreationDto.setMarkWithModelDtos(markWithModelDtos);
        carCreationDto.setFuelDtos(fuelDtos);
        carCreationDto.setCategoryDtos(categoryDtos);

        model.addAttribute("carCreationDto", carCreationDto);
        model.addAttribute("errorMessage", ERROR_CAR_CREATION_MAPPING);
        model.addAttribute("nbMaxPlaces", NUMBER_PLACES_MAX);
    }

}
