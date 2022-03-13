package com.negra.location.service;

import com.negra.location.entity.Adresse;
import com.negra.location.entity.Agent;
import com.negra.location.repository.AdresseRepository;
import com.negra.location.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AdresseService adresseService;

    public void createAgent(Agent agent){
        agentRepository.save(agent);
    }

    // Suppression d'agent après la suppression de l'adresse associe s'elle existe
    public void deleteAgent(Agent agent){
        Adresse adresse = agent.getAdresse();
        if(adresse != null)
            adresseService.deleteAdresse(adresse);

        agentRepository.delete(agent);
    }

}
