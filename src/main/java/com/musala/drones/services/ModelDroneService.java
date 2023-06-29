package com.musala.drones.services;

import com.musala.drones.entities.ModelDrone;
import com.musala.drones.repositories.ModelDroneRepository;
import org.springframework.stereotype.Service;

@Service
public class ModelDroneService extends BasicServices<ModelDrone, Long>{

    public ModelDroneService(ModelDroneRepository modelDroneRepository) {
        super(modelDroneRepository, "Model of Drone");
    }
}
