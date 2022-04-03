package com.negra.location.service.interfaces;

import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.VisitNotCountedException;
import com.negra.location.model.Car;

public interface IVisitService {

    void initializeVisit(Long id) throws DataNotFoundException, VisitNotCountedException;

}
