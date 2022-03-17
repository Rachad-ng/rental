package com.negra.location.service;

import com.negra.location.model.Frais;
import com.negra.location.model.Voiture;
import com.negra.location.repository.FraisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FraisService {

    @Autowired
    private FraisRepository fraisRepository;

    public void createFrais(Frais frais, Voiture voiture){
        voiture.addFrais(frais);
        fraisRepository.save(frais);
    }

    public void deleteFrais(Frais frais){
        frais.getVoiture().removeFrais(frais);
        fraisRepository.delete(frais);
    }
}
