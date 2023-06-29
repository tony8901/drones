package com.musala.drones.entities;

import jakarta.persistence.*;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "weight_limit", nullable = false)
    private int weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private int batteryCapacity;

    @ManyToOne
    @JoinColumn(name = "model_drone_id", nullable = false)
    private ModelDrone modelDrone;

    @ManyToOne
    @JoinColumn(name = "state_drone_id")
    private StateDrone stateDrone;

    public StateDrone getStateDrone() {
        return stateDrone;
    }

    public void setStateDrone(StateDrone stateDrone) {
        this.stateDrone = stateDrone;
    }

    public ModelDrone getModelDrone() {
        return modelDrone;
    }

    public void setModelDrone(ModelDrone modelDrone) {
        this.modelDrone = modelDrone;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
