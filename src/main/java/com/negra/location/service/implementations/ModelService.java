package com.negra.location.service.implementations;

import com.negra.location.dto.MarkDto;
import com.negra.location.dto.ModelWithImageAndMarkDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Mark;
import com.negra.location.model.Model;
import com.negra.location.repository.MarkRepository;
import com.negra.location.repository.ModelRepository;
import com.negra.location.service.interfaces.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.negra.location.utility.ErrorMessage.*;

@Service
@Transactional
public class ModelService implements IModelService {

    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private MarkRepository markRepository;

    @Override
    public void createModel(Model model, Mark mark){
        mark.addModel(model);
        try {
            modelRepository.save(model);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    @Override
    public Model findById(long id) throws DataNotFoundException{
        Model model;
        Optional<Model> optionalModel;

        try {
            optionalModel = modelRepository.findById(id);
        }catch (Exception e){
            throw new DataNotFoundException(ERROR_MODEL_LIBELLE);
        }

        if(optionalModel.isPresent())
            model = optionalModel.get();
        else
            throw new DataNotFoundException(ERROR_MODEL_LIBELLE);

        return model;
    }

    @Override
    public void deleteModel(Model model){
        model.getMark().removeModel(model);
        try {
            modelRepository.delete(model);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public  List<ModelWithImageAndMarkDto> getByMark(long idMark){
        List<ModelWithImageAndMarkDto> resultat = null;
        Optional<Mark> mark = markRepository.findById(idMark);
        if(mark.isPresent()){

            Set<Model> modelSet = mark.get().getModelSet();
            List<Model> models = new ArrayList<>(modelSet).stream().sorted(Comparator.comparing(Model::getLibelle)).collect(Collectors.toList());
            List<ModelWithImageAndMarkDto> modelWithImageAndMarkDtos = new ArrayList<>();
            for (Model model: models)
                modelWithImageAndMarkDtos.add(
                        new ModelWithImageAndMarkDto(
                                model.getId(),
                                model.getLibelle(),
                                model.getImage(),
                                new MarkDto(
                                        model.getMark().getId(),
                                        model.getMark().getLibelle())));

            resultat = modelWithImageAndMarkDtos;
        }else
            throw new DataNotFoundException(ERROR_MARK_NOT_FOUND);

        return resultat;
    }
}
