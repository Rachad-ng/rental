package com.negra.location.service.implementations;

import com.negra.location.dto.CategoryDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Category;
import com.negra.location.repository.CategoryRepository;
import com.negra.location.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.negra.location.utility.ErrorMessage.ERROR_CATEGORY_NOT_FOUND;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(long id){
        Optional<Category> optionalCategorie = categoryRepository.findById(id);
        if(optionalCategorie.isPresent())
            return optionalCategorie.get();
        else
            throw new DataNotFoundException(ERROR_CATEGORY_NOT_FOUND);
    }

    @Override
    public List<CategoryDto> findAllDtos(){
        try{
            List<Category> categories = categoryRepository.findAll();
            List<CategoryDto> categoryDtos = new ArrayList<>();
            MapperService.categoriesToCategoryDtos(categories, categoryDtos);
            return categoryDtos;
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

}
