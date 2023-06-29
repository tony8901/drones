package com.musala.drones.services;

import com.musala.drones.entities.StateDrone;
import com.musala.drones.repositories.StateDroneRepository;
import org.springframework.stereotype.Service;

@Service
public class StateDroneService extends BasicServices<StateDrone, Long> {
    public StateDroneService(StateDroneRepository stateDroneRepository) {
        super(stateDroneRepository, "State of Drone");
    }
}
