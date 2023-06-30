package com.musala.drones.controllers;

import com.musala.drones.entities.ModelDrone;
import com.musala.drones.services.ModelDroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/models")
public class ModelDroneController {

    private final ModelDroneService modelDroneService;

    public ModelDroneController(ModelDroneService modelDroneService) {
        this.modelDroneService = modelDroneService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){ return modelDroneService.findAll(); }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ModelDrone modelDrone){ return modelDroneService.save(modelDrone);}

    @PostMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){ return modelDroneService.findById(id);}

    @PostMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){ return modelDroneService.findByName(name);}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){ return modelDroneService.deleteById(id);}
}
