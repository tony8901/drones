package com.musala.drones.services;

import com.musala.drones.entities.Medication;
import com.musala.drones.repositories.MedicationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;

@Service
public class MedicationService extends BasicServices<Medication, Long>{

    private final MedicationRepository medicationRepository;
    public MedicationService(MedicationRepository medicationRepository) {
        super(medicationRepository, "Medication");
        this.medicationRepository = medicationRepository;
    }

    public ResponseEntity<?> findByName(String name) {
        try {
            List<Medication> medications = medicationRepository.findByNameIgnoreCase(name);

            return medications.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medication not found with name: " + name)
                    : ResponseEntity.ok(medications);
        } catch (Exception e) {
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }

}
