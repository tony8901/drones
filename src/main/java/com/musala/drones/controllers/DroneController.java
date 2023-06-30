package com.musala.drones.controllers;

import com.musala.drones.entities.Drone;
import com.musala.drones.services.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){ return droneService.findAll();}

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Drone drone){ return droneService.save(drone); }

    @PostMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){ return droneService.findById(id); }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){ return droneService.deleteById(id); }
}
