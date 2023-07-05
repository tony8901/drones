package com.musala.drones.controllers;

import com.musala.drones.services.ManagementDroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/management/drones")
public class ManagementDroneController {

    private final ManagementDroneService managementDroneService;

    public ManagementDroneController(ManagementDroneService managementDroneService) {
        this.managementDroneService = managementDroneService;
    }

    @PostMapping("/{drone}/charge")
    public ResponseEntity<?> loadingDronMedications(@PathVariable Long drone, @RequestParam List<Long> id){
        return managementDroneService.loadingDronMedications(drone, id);
    }
}
