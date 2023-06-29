package com.musala.drones.repositories;

import com.musala.drones.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    List<Medication> findByNameIgnoreCase(String name);

    boolean existsByName(String name);
}