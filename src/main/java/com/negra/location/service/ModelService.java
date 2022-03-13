package com.negra.location.service;

import com.negra.location.entity.Marque;
import com.negra.location.entity.Model;
import com.negra.location.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public void createModel(Model model, Marque marque){
        marque.addModel(model);
        modelRepository.save(model);
    }

    public void deleteModel(Model model){
        model.getMarque().removeModel(model);
        modelRepository.delete(model);
    }
}
