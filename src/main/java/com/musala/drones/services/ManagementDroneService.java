package com.musala.drones.services;

import com.musala.drones.dto.DroneCapacityDTO;
import com.musala.drones.entities.AuditEvent;
import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.entities.StateDrone;
import com.musala.drones.repositories.AuditEventRepository;
import com.musala.drones.repositories.DroneRepository;
import com.musala.drones.repositories.MedicationRepository;
import com.musala.drones.repositories.StateDroneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ManagementDroneService {

    public static final int MAX_CAPACITY = 500;

    private final DroneRepository droneRepository;

    private final MedicationRepository medicationRepository;

    private final StateDroneRepository stateDroneRepository;

    private final AuditEventRepository auditEventRepository;


    public ManagementDroneService(DroneRepository droneRepository,
                                  MedicationRepository medicationRepository,
                                  StateDroneRepository stateDroneRepository,
                                  AuditEventRepository auditEventRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
        this.stateDroneRepository = stateDroneRepository;
        this.auditEventRepository = auditEventRepository;
    }

    public ResponseEntity<?> loadingDronMedications(Long droneId, List<Long> medicationListId) {
        try {
            Drone drone = droneRepository.findById(droneId).get();
            StateDrone state = drone.getStateDrone();

            if(!state.getName().equals("IDLE")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The drone is already "+state.getName()+"!");
            } else if (drone.getBatteryCapacity() < 25) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Drone battery is below 25%!");
            }
            List<StateDrone> stateDrone = stateDroneRepository.findByNameIgnoreCase("LOADING");
            if (!stateDrone.isEmpty()){
                StateDrone stateDrone1 = stateDrone.get(0);
                drone.setStateDrone(stateDrone1);
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error loading drone states!");
            }

            List<Medication> medicationList = drone.getMedications();
            int capacityCharged = checkCapacity(drone);

            for (Long medicationId : medicationListId) {
                Medication medication = medicationRepository.findById(medicationId).get();
                int medicationWeight = medication.getWeight();
                if (capacityCharged + medicationWeight <= MAX_CAPACITY) {
                    medicationList.add(medication);
                    capacityCharged += medication.getWeight();
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The drone does not support more weight!");
                }
            }

            drone.setMedications(medicationList);
            drone.setStateDrone(state);
            droneRepository.save(drone);
            return ResponseEntity.status(HttpStatus.OK).body(drone);
        } catch (Exception e) {
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }

    public ResponseEntity<?> checkDroneCharge(Long id) {
        try {
            Drone drone = droneRepository.findById(id).get();
            List<Medication> medicationList = new ArrayList<>(drone.getMedications());
            return ResponseEntity.status(HttpStatus.OK).body(medicationList);

        } catch (Exception e) {
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }

    public ResponseEntity<?> availableDrones() {
        try {
            List<Drone> droneList = droneRepository.findAll();
            List<DroneCapacityDTO> availableDrones = new ArrayList<>();

            if (droneList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drones not found!");
            }

            for (Drone drone : droneList) {
                int capacityCharged = checkCapacity(drone);
                int weightLimit = drone.getWeightLimit();

                if (capacityCharged < weightLimit) {
                    int availableCapacity = weightLimit - capacityCharged;
                    availableDrones.add(new DroneCapacityDTO(drone, availableCapacity));
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(availableDrones);

        } catch (Exception e) {
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }

    public ResponseEntity<?> checkBattery(Long id) {
        try {
            if (droneRepository.findById(id).isPresent()) {
                Drone drone = droneRepository.findById(id).get();
                HashMap<String, Integer> checkBattery = new HashMap<>();
                checkBattery.put("batteryCapacity", drone.getBatteryCapacity());
                return ResponseEntity.status(HttpStatus.OK).body(checkBattery);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drone not found with id: " + id);

        } catch (Exception e) {
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }

    @Scheduled(fixedRate = 300000)
    public void checkDronesBatteryLevels(){
        try{
            List<Drone> droneList = droneRepository.findAll();
            for (Drone drone: droneList){
                AuditEvent auditEvent = new AuditEvent();
                auditEvent.setEventType("checking_battery_capacity");
                auditEvent.setDescription("Battery of drone "+drone.getId()+" is "+drone.getBatteryCapacity()+"%.\n");
                auditEvent.setTimestamp(LocalDateTime.now());
                auditEventRepository.save(auditEvent);
            }
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
