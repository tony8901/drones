package com.musala.drones.services;

import com.musala.drones.dto.MedicationDTO;
import com.musala.drones.entities.Medication;
import com.musala.drones.repositories.MedicationRepository;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MedicationService extends BasicServiceWithNamedEntity<Medication, Long>{

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        super(medicationRepository, "Medication");
        this.medicationRepository = medicationRepository;
    }

    public ResponseEntity<?> create(MultipartFile image, String name, String code, int weight) {
        try{
            if(!image.getContentType().equals("image/jpeg")){
                throw new ErrorResponseException(
                        HttpStatus.BAD_REQUEST,
                        ProblemDetail.forStatusAndDetail(
                                HttpStatus.BAD_REQUEST,
                                "The file type must be a jpg image!"),
                        null
                );
            }
            byte[] imageByte = image.getBytes();
            MedicationDTO medicationDTO = new MedicationDTO();
            medicationDTO.setImage(imageByte);
            medicationDTO.setName(name);
            medicationDTO.setCode(code);
            medicationDTO.setWeight(weight);

            return ResponseEntity.status(HttpStatus.OK).body(medicationRepository.save(medicationDTO.getMedication()));
        } catch (Exception e){
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }
}
