package com.musala.drones.services;

import com.musala.drones.entities.ModelDrone;
import com.musala.drones.repositories.ModelDroneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.Optional;

@Service
public class ModelDroneService extends BasicServiceWithNamedEntity<ModelDrone, Long>{

    private final ModelDroneRepository modelDroneRepository;

    public ModelDroneService(ModelDroneRepository modelDroneRepository) {
        super(modelDroneRepository, "Model of Drone");
        this.modelDroneRepository = modelDroneRepository;
    }

    public ModelDrone findModelDroneById(Long id){
        try{
            Optional optional = modelDroneRepository.findById(id);
            ModelDrone modelDrone = (ModelDrone) optional.get();
            return modelDrone;
        }catch (Exception e){
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }
}
