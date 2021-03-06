package com.negra.location.service.implementations;

import com.negra.location.dto.FuelDto;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.DataStoreException;
import com.negra.location.mapper.FuelDtoMapper;
import com.negra.location.model.Fuel;
import com.negra.location.repository.FuelRepository;
import com.negra.location.service.interfaces.IFuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.negra.location.utility.ErrorMessage.ERROR_FUEL_NOT_FOUND;
import static com.negra.location.utility.ErrorMessage.ERROR_DATA;

@Service
@Transactional
public class FuelService implements IFuelService {

    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private FuelDtoMapper fuelDtoMapper;

    @Override
    public List<FuelDto> findAllDtos(){
        List<Fuel> fuels;
        try{
            fuels = fuelRepository.findAll().stream().sorted(Comparator.comparing(Fuel::getLibelle)).collect(Collectors.toList());
            return fuelDtoMapper.fuelToFuelDto(fuels);
        }catch (Exception e){
            throw new DataStoreException(ERROR_DATA);
        }
    }

    @Override
    public Fuel findById(long idFuel) throws DataNotFoundException {
        Fuel fuel;
        Optional<Fuel> optionalCarburant;
        try {
            optionalCarburant = fuelRepository.findById(idFuel);
        }catch (Exception e){
            throw new DataNotFoundException(ERROR_FUEL_NOT_FOUND);
        }

        if(optionalCarburant.isPresent())
            fuel = optionalCarburant.get();
        else
            throw new DataNotFoundException(ERROR_FUEL_NOT_FOUND);

        return fuel;
    }
}
