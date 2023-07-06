package com.musala.drones.dto;

import com.musala.drones.entities.Drone;
import com.musala.drones.entities.Medication;
import com.musala.drones.services.MedicationService;
import com.musala.drones.services.ModelDroneService;
import com.musala.drones.services.StateDroneService;

import java.util.ArrayList;
import java.util.List;

public class DroneDTO {

    private ModelDroneService modelDroneService;
    private StateDroneService stateDroneService;
    private MedicationService medicationService;

    private Long id;
    private String serialNumber;
    private int weightLimit;
    private int batteryCapacity;
    private Long modelDroneId;
    private Long stateDroneId;

    private List<Long> medications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Long getModelDroneId() {
        return modelDroneId;
    }

    public void setModelDroneId(Long modelDroneId) {
        modelDroneId = modelDroneId;
    }

    public Long getStateDroneId() {
        return stateDroneId;
    }

    public void setStateDroneId(Long stateDroneId) {
        stateDroneId = stateDroneId;
    }

    public List<Long> getMedications() {
        return medications;
    }

    public void setMedications(List<Long> medications) {
        this.medications = medications;
    }

    public Drone toEntity(){
        Drone drone = new Drone();

        drone.setId(id);
        drone.setBatteryCapacity(batteryCapacity);
        drone.setSerialNumber(serialNumber);
        drone.setWeightLimit(weightLimit);
        drone.setModelDrone(modelDroneService.findModelDroneById(modelDroneId));
        drone.setStateDrone(stateDroneService.findStateDroneById(stateDroneId));
        List<Medication> medicationList = new ArrayList<>();
        for(Long medicationId : medications){
            medicationList.add(medicationService.findMedicationById(medicationId));
        }
        drone.setMedications(medicationList);

        return drone;
    }

    public DroneDTO toDTO(Drone drone){
        if(drone == null){
            return null;
        }

        DroneDTO droneDTO = new DroneDTO();

        droneDTO.setId(drone.getId());
        droneDTO.setSerialNumber(drone.getSerialNumber());
        droneDTO.setBatteryCapacity(drone.getBatteryCapacity());
        droneDTO.setWeightLimit(drone.getWeightLimit());
        droneDTO.setModelDroneId(drone.getModelDrone().getId());
        droneDTO.setStateDroneId(drone.getStateDrone().getId());
        List<Long> medicationList = new ArrayList<>();
        for(Medication medication : drone.getMedications()){
            medicationList.add(medication.getId());
        }
        droneDTO.setMedications(medicationList);

        return droneDTO;
    }

}
