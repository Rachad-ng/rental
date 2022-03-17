package com.negra.location.service;

import com.negra.location.model.Location;
import com.negra.location.model.Reduction;
import com.negra.location.model.Reservation;
import com.negra.location.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public void createLocation(Location location, Reservation reservation, Reduction reduction){
        reservation.addLocation(location);
        reduction.addLocation(location);
        locationRepository.save(location);
    }

    public void deleteLocation(Location location){
        location.getReservation().removeLocation(location);
        location.getReduction().removeLocation(location);
        locationRepository.delete(location);
    }

}
