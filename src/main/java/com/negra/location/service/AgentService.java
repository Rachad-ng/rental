package com.negra.location.service;

import com.negra.location.dto.AgentRegistrationDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.model.Adresse;
import com.negra.location.model.Agent;
import com.negra.location.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private AdresseService adresseService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createAgent(AgentRegistrationDto agentRegistrationDto) throws AlreadyExistsException
    {
        utilisateurService.isUserExists(agentRegistrationDto.getEmail());
        Agent agent = new Agent();
        Adresse adresse = new Adresse();
        mapperAgentRegitrationDtoToEntities(agentRegistrationDto, agent, adresse);
        agentRepository.save(agent);
        adresseService.createAdresse(adresse, agent);
    }

    // Suppression d'agent après la suppression de l'adresse associe s'elle existe
    public void deleteAgent(Agent agent){
        Adresse adresse = agent.getAdresse();
        if(adresse != null)
            adresseService.deleteAdresse(adresse);

        agentRepository.delete(agent);
    }

    private void mapperAgentRegitrationDtoToEntities(AgentRegistrationDto agentRegistrationDto, Agent agent, Adresse adresse){
        agent.setNom(agentRegistrationDto.getNom());
        agent.setPrenom(agentRegistrationDto.getPrenom());
        agent.setEmail(agentRegistrationDto.getEmail());
        agent.setPassword(passwordEncoder.encode(agentRegistrationDto.getPassword()));
        agent.setTel(agentRegistrationDto.getTel());
        agent.setRsAgence(agentRegistrationDto.getRsAgence());
        agent.setRole(agentRegistrationDto.getRole());

        adresse.setNumero(agentRegistrationDto.getNumero());
        adresse.setRue(agentRegistrationDto.getRue());
        adresse.setQuartier(agentRegistrationDto.getQuartier());
        adresse.setVille(agentRegistrationDto.getVille());
        adresse.setPays(agentRegistrationDto.getPays());
    }

}
