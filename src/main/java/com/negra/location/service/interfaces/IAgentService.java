package com.negra.location.service.interfaces;

import com.negra.location.dto.AgentRegistrationDto;
import com.negra.location.dto.CarCreationDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Agent;
import org.springframework.ui.Model;

public interface IAgentService {

    void createAgent(AgentRegistrationDto agentRegistrationDto) throws AlreadyExistsException, DataStoreException;
    void deleteAgent(Agent agent) throws DataStoreException;
    void initialisationCarCreationFrom(Model model) throws DataStoreException;
    void recuperationInputData(CarCreationDto carCreationDto, Model model) throws DataNotFoundException, DataStoreException;

}
