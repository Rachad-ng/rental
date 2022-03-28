package com.negra.location.service.interfaces;

import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Reduction;

public interface IReductionService {

    void createReduction(Reduction reduction) throws DataStoreException;
}
