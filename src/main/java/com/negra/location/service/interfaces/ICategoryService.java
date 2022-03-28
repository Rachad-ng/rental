package com.negra.location.service.interfaces;

import com.negra.location.dto.CategoryDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Category;

import java.util.List;

public interface ICategoryService {

    Category findById(long id) throws DataNotFoundException;
    List<CategoryDto> findAllDtos() throws DataStoreException;
}
