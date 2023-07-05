package com.musala.drones.dto;

import com.musala.drones.entities.Medication;

public class MedicationDTO {

    private String name;
    private int weight;
    private String code;
    private byte[] image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Medication toEntity(){
        Medication medication = new Medication();

        medication.setName(name);
        medication.setCode(code);
        medication.setWeight(weight);
        medication.setImage(image);

        return medication;
    }
}
