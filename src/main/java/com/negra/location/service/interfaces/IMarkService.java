package com.negra.location.service.interfaces;

import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Mark;

import java.util.List;

public interface IMarkService {

    void createMarque(Mark mark) throws DataStoreException;
    void deleteMarque(Mark mark) throws DataStoreException;
    List<Mark> findAll() throws DataStoreException;
    Mark findById(long id) throws DataNotFoundException;
}
