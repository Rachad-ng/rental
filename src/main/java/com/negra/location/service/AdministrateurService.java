package com.negra.location.service;

import com.negra.location.entity.Administrateur;
import com.negra.location.repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    public void createAdministrateur(Administrateur administrateur){
        administrateurRepository.save(administrateur);
    }

}
