package com.negra.location.service;

import com.negra.location.model.Administrateur;
import com.negra.location.repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Autowired
    private UtilisateurService utilisateurService;

    public void createAdministrateur(Administrateur administrateur){
        utilisateurService.isUserExists(administrateur.getEmail());
        administrateurRepository.save(administrateur);
    }

}
