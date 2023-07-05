package com.musala.drones.services;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.repositories.DroneRepository;
import com.musala.drones.repositories.MedicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;

@Service
public class ManagementDroneService {

    public static final int MAX_CAPACITY = 500;

    private final DroneRepository droneRepository;

    private final MedicationRepository medicationRepository;

    private final DroneService droneService;

    public ManagementDroneService(DroneRepository droneRepository, MedicationRepository medicationRepository, DroneService droneService) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
        this.droneService = droneService;
    }

    public ResponseEntity<?> loadingDronMedications(Long droneId, List<Long> medicationListId) {
        try {
            Drone drone = droneRepository.findById(droneId).get();
            List<Medication> medicationList = drone.getMedications();
            int capacityCharged = checkCapacity(drone);


            if (!medicationListId.isEmpty()) {
                for (Long medicationId : medicationListId) {
                    Medication medication = medicationRepository.findById(medicationId).get();
                    int medicationWeight = medication.getWeight();
                    if (capacityCharged + medicationWeight <= MAX_CAPACITY) {
                        medicationList.add(medication);
                        capacityCharged+= medication.getWeight();
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The drone does not support more weight!");
                    }
                }
                drone.setMedications(medicationList);
                return ResponseEntity.status(HttpStatus.OK).body(droneRepository.save(drone));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No charges were provided!");
        } catch (Exception e) {
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }

    private int checkCapacity(Drone drone) {
        int capacityCharged = 0;

        for (Medication medication : drone.getMedications()) {
            capacityCharged += medication.getWeight();
        }

        return capacityCharged;
    }

}
