package com.musala.drones.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "serial_number", nullable = false, length = 100, unique = true)
    private String serialNumber;

    @Column(name = "weight_limit", nullable = false)
    private int weightLimit;

    @Min(0)
    @Max(100)
    @Column(name = "battery_capacity", nullable = false)
    private int batteryCapacity;

    @ManyToOne
    @JoinColumn(name = "model_drone_id", nullable = false)
    private ModelDrone modelDrone;

    @ManyToOne
    @JoinColumn(name = "state_drone_id")
    private StateDrone stateDrone;

    @ManyToMany
    @JoinTable(name = "drone_medications",
            joinColumns = @JoinColumn(name = "drone_id"),
            inverseJoinColumns = @JoinColumn(name = "medications_id"))
    private List<Medication> medications = new ArrayList<>();

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public StateDrone getStateDrone() {
        return stateDrone;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
