package com.musala.drones.repositories;

import com.musala.drones.entities.StateDrone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDroneRepository extends JpaRepository<StateDrone, Long> {
}