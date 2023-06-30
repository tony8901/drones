package com.musala.drones.controllers;

import com.musala.drones.entities.Medication;
import com.musala.drones.entities.ModelDrone;
import com.musala.drones.services.MedicationService;
import jakarta.annotation.Nullable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){ return medicationService.findAll(); }

//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody Medication medication){ return medicationService.save(medication);}

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> save(@RequestParam("image") MultipartFile image,
                                  @RequestParam("name") String name,
                                  @RequestParam("code") String code,
                                  @RequestParam("weight") int weight){
        return medicationService.create(image, name, code, weight);
    }

    @PostMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){ return medicationService.findById(id);}

    @PostMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){ return medicationService.findByName(name);}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){ return medicationService.deleteById(id);}
}
