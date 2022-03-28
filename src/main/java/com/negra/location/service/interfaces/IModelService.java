package com.negra.location.service.interfaces;

import com.negra.location.dto.ModelWithImageDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Mark;
import com.negra.location.model.Model;

import java.util.List;

public interface IModelService {

    void createModel(Model model, Mark mark) throws DataStoreException;
    Model findById(long id) throws DataNotFoundException;
    void deleteModel(Model model) throws DataStoreException;
    List<ModelWithImageDto> getByMark(long idMark) throws DataNotFoundException;

}
