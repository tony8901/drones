package com.musala.drones.controllers;

import com.musala.drones.entities.ModelDrone;
import com.musala.drones.entities.StateDrone;
import com.musala.drones.services.StateDroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/states")
public class StateDroneController {

    private final StateDroneService stateDroneService;

    public StateDroneController(StateDroneService stateDroneService) {
        this.stateDroneService = stateDroneService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){ return stateDroneService.findAll(); }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody StateDrone stateDrone){ return stateDroneService.save(stateDrone);}

    @PostMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){ return stateDroneService.findById(id);}

    @PostMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){ return stateDroneService.findByName(name);}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){ return stateDroneService.deleteById(id);}
}
