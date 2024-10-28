package com.g4appdev.LostIT.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.g4appdev.LostIT.entity.LocationEntity;
import com.g4appdev.LostIT.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Endpoint to create a new location
    @PostMapping("/createLocation")
    public LocationEntity createLocation(@RequestBody LocationEntity locationEntity) {
        return locationService.createLocation(locationEntity);
    }

    // Endpoint to get all locations
    @GetMapping("/getAllLocations")
    public List<LocationEntity> getAllLocations() {
        return locationService.getAllLocations();
    }

    // Endpoint to update location details
    @PutMapping("/updateLocationDetails/")
    public LocationEntity updateLocationDetails(@RequestParam int id, @RequestBody LocationEntity newLocationDetails) {
        return locationService.updateLocationDetails(id, newLocationDetails);
    }

    // Endpoint to delete a location by ID
    @DeleteMapping("/deleteLocation/{id}")
    public String deleteLocation(@PathVariable int id) {
        return locationService.deleteLocation(id);
    }
}

