package com.negra.location.service.implementations;

import com.negra.location.dto.MarkDto;
import com.negra.location.dto.ModelWithImageDto;
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

    public void createModel(Model model, Mark mark){
        mark.addModel(model);
        try {
            modelRepository.save(model);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    public Model findById(long id) throws DataNotFoundException{
        Model model;
        Optional<Model> optionalModel = modelRepository.findById(id);
        if(optionalModel.isPresent())
            model = optionalModel.get();
        else
            throw new DataNotFoundException(ERROR_MODEL_LIBELLE);

        return model;
    }

    public void deleteModel(Model model){
        model.getMark().removeModel(model);
        try {
            modelRepository.delete(model);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    public  List<ModelWithImageDto> getByMark(long idMark){
        List<ModelWithImageDto> resultat = null;
        Optional<Mark> mark = markRepository.findById(idMark);
        if(mark.isPresent()){

            Set<Model> modelSet = mark.get().getModelSet();
            List<Model> models = new ArrayList<>(modelSet).stream().sorted(Comparator.comparing(Model::getLibelle)).collect(Collectors.toList());
            List<ModelWithImageDto> modelWithImageDtos = new ArrayList<>();
            for (Model model: models)
                modelWithImageDtos.add(
                        new ModelWithImageDto(
                                model.getId(),
                                model.getLibelle(),
                                model.getImage(),
                                new MarkDto(
                                        model.getMark().getId(),
                                        model.getMark().getLibelle())));

            resultat = modelWithImageDtos;
        }else
            throw new DataNotFoundException(ERROR_MARK_NOT_FOUND);

        return resultat;
    }
}
