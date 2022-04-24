package com.negra.location.service.interfaces;

import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Reduction;

import java.util.List;

public interface IReductionService {

    void createReduction(Reduction reduction) throws DataStoreException;

    List<Reduction> findAll() throws DataNotFoundException;
}
