package com.musala.drones.repositories;

import com.musala.drones.entities.ModelDrone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelDroneRepository extends BasicRepositoryWithNamedEntity<ModelDrone, Long> {
}