package com.negra.location.service.implementations;

import com.negra.location.model.Administrator;
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

    public void createAdministrateur(Administrator administrator){
        utilisateurService.isUserExists(administrator.getEmail());
        administrateurRepository.save(administrator);
    }

}
