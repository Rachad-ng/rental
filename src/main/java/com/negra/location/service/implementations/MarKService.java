package com.negra.location.service.implementations;

import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Mark;
import com.negra.location.model.Model;
import com.negra.location.repository.MarkRepository;
import com.negra.location.service.interfaces.IMarkService;
import com.negra.location.service.interfaces.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.negra.location.utility.ErrorMessage.*;

@Service
@Transactional
public class MarKService implements IMarkService {

    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private IModelService modelService;

    // Creation d'une marque
    @Override
    public void createMarque(Mark mark){
        try{
            markRepository.save(mark);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    // Supprimer la marque après la suppression des models associes
    @Override
    public void deleteMarque(Mark mark){
        Set<Model> models = mark.getModelSet();
        try {
            for (Model model: models) {
                modelService.deleteModel(model);
            }
            markRepository.delete(mark);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    // Recuperations de tous les marques
    @Override
    public List<Mark> findAll(){
        try{
            List<Mark> marks = markRepository.getAllMarkOrderedByLibelle();
            return marks;
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public Mark findById(long id){

        Optional<Mark> optionalMarque = markRepository.findById(id);
        if(optionalMarque.isPresent())
            return optionalMarque.get();
        else
            throw new DataNotFoundException(ERROR_MARK_NOT_FOUND);
    }

}
