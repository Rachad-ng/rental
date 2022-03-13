package com.negra.location.service;

import com.negra.location.entity.Utilisateur;
import com.negra.location.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void createUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }

    // On doit gérer la suppression au niveau des classes filles, puisqu'elles sont en relation avec des autres classes

}
