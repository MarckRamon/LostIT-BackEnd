package com.g4appdev.LostIT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4appdev.LostIT.entity.LocationEntity;
import com.g4appdev.LostIT.repository.LocationRepo;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public LocationEntity createLocation(LocationEntity locationEntity) {
        return locationRepo.save(locationEntity);
    }

    public List<LocationEntity> getAllLocations() {
        return locationRepo.findAll();
    }

    public LocationEntity updateLocationDetails(int id, LocationEntity newLocationDetails) {
        LocationEntity locationEntity;
        try {
            locationEntity = locationRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Location not found"));

            locationEntity.setLocationBuilding(newLocationDetails.getLocationBuilding());
            locationEntity.setLocationFloor(newLocationDetails.getLocationFloor());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Location with ID " + id + " not found!");
        }
        return locationRepo.save(locationEntity);
    }

    public String deleteLocation(int id) {
        if (locationRepo.existsById(id)) {
            locationRepo.deleteById(id);
            return "Location record successfully deleted!";
        } else {
            return "Location with ID " + id + " NOT FOUND!";
        }
    }
}

