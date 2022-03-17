package com.negra.location.service;

import com.negra.location.model.Entretien;
import com.negra.location.model.Voiture;
import com.negra.location.repository.EntretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EntretienService {

    @Autowired
    private EntretienRepository entretienRepository;

    public void createEntretien(Entretien entretien, Voiture voiture){
        voiture.addEntretien(entretien);
        entretienRepository.save(entretien);
    }

    public void deleteEntretien(Entretien entretien){
        entretien.getVoiture().removeEntretien(entretien);
        entretienRepository.delete(entretien);
    }

}
