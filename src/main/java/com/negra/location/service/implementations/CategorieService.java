package com.negra.location.service.implementations;

import com.negra.location.dto.CategorieDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Category;
import com.negra.location.repository.CategorieRepository;
import com.negra.location.service.interfaces.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.negra.location.utility.ErrorMessage.ERROR_CATEGORIE_NOT_FOUND;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA;

@Service
@Transactional
public class CategorieService implements ICategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public Category findById(long id){
        Optional<Category> optionalCategorie = categorieRepository.findById(id);
        if(optionalCategorie.isPresent())
            return optionalCategorie.get();
        else
            throw new DataNotFoundException(ERROR_CATEGORIE_NOT_FOUND);
    }

    public List<CategorieDto> findAllDtos(){
        try{
            List<Category> categories = categorieRepository.findAll();
            List<CategorieDto> categorieDtos = new ArrayList<>();
            MapperService.categoriesToCategorieDtos(categories, categorieDtos);
            return categorieDtos;
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}
