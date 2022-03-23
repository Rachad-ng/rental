package com.negra.location.service.interfaces;

import com.negra.location.dto.CategorieDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Category;

import java.util.List;

public interface ICategorieService {

    Category findById(long id) throws DataNotFoundException;
    List<CategorieDto> findAllDtos() throws DataStoreException;
}
