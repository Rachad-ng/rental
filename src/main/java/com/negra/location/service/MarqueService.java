package com.negra.location.service;

import com.negra.location.model.Marque;
import com.negra.location.model.Model;
import com.negra.location.repository.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private ModelService modelService;

    public void createMarque(Marque marque){
        marqueRepository.save(marque);
    }

    // Supprimer la marque après la suppression des models associes
    public void deleteMarque(Marque marque){
        Set<Model> models = marque.getModels();
        for (Model model: models) {
            modelService.deleteModel(model);
        }
        marqueRepository.delete(marque);
    }

}
