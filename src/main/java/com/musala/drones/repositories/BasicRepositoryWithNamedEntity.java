package com.musala.drones.repositories;

import com.musala.drones.entities.Medication;
import com.musala.drones.entities.NamedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BasicRepositoryWithNamedEntity<T extends NamedEntity, ID> extends JpaRepository<T, ID> {
    List<T> findByNameIgnoreCase(String name);
}
