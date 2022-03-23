package com.negra.location.service.interfaces;

import com.negra.location.dto.FuelDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.model.Fuel;

import java.util.List;

public interface IFuelService {

    List<FuelDto> findAllDtos() throws DataStoreException;
    Fuel findById(long idFuel) throws DataNotFoundException;
}
