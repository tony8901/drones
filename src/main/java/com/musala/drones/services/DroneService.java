package com.musala.drones.services;

import com.musala.drones.entities.Drone;
import com.musala.drones.repositories.DroneRepository;
import org.springframework.stereotype.Service;

@Service
public class DroneService extends BasicServices<Drone, Long>{

    public DroneService(DroneRepository droneRepository) {
        super(droneRepository, "Drone");
    }
}
