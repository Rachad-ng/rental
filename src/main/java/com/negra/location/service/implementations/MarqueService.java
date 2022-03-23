package com.negra.location.service.implementations;

import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Mark;
import com.negra.location.model.Model;
import com.negra.location.repository.MarqueRepository;
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
public class MarqueService implements IMarkService {

    @Autowired
    private MarqueRepository marqueRepository;
    @Autowired
    private IModelService modelService;

    // Creation d'une marque
    public void createMarque(Mark mark){
        try{
            marqueRepository.save(mark);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA_STORING);
        }
    }

    // Supprimer la marque après la suppression des models associes
    public void deleteMarque(Mark mark){
        Set<Model> models = mark.getModelSet();
        try {
            for (Model model: models) {
                modelService.deleteModel(model);
            }
            marqueRepository.delete(mark);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    // Recuperations de tous les marques
    public List<Mark> findAll(){
        try{
            List<Mark> marks = marqueRepository.findAll().stream()
                    .sorted( Comparator.comparing( Mark::getLibelle ) )
                    .collect( Collectors.toList() );

            return marks;
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    public Mark findById(long id){

        Optional<Mark> optionalMarque = marqueRepository.findById(id);
        if(optionalMarque.isPresent())
            return optionalMarque.get();
        else
            throw new DataNotFoundException(ERROR_MARK_NOT_FOUND);
    }

}
