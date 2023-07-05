package com.musala.drones.dto;

import com.musala.drones.entities.Drone;

public class DroneCapacityDTO {
    private DroneCapacityDTO(){}

    private Drone drone;
    private int availableCapacity;

    public DroneCapacityDTO(Drone drone, int availableCapacity) {
        this.drone = drone;
        this.availableCapacity = availableCapacity;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
