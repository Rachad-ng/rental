package com.negra.location.service;

import com.negra.location.entity.Adresse;
import com.negra.location.entity.Agent;
import com.negra.location.repository.AdresseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdresseService {

    @Autowired
    private AdresseRepository adresseRepository;

    public void createAdresse(Adresse adresse, Agent agent){
        agent.addAdresse(adresse);
        adresseRepository.save(adresse);
    }

    public void deleteAdresse(Adresse adresse){
        adresse.getAgent().removeAdresse(adresse);
    }

}
