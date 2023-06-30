package com.musala.drones.services;

import com.musala.drones.entities.NamedEntity;
import com.musala.drones.repositories.BasicRepositoryWithNamedEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;

import java.util.List;

public class BasicServiceWithNamedEntity<T extends NamedEntity, ID> extends BasicServices<T, ID>{

    private final BasicRepositoryWithNamedEntity<T, ID> basicRepositoryWithNamedEntity;

    private final String nameEntity;

    public BasicServiceWithNamedEntity(BasicRepositoryWithNamedEntity<T, ID> basicRepositoryWithNamedEntity, String nameEntity) {
        super(basicRepositoryWithNamedEntity, nameEntity);
        this.basicRepositoryWithNamedEntity = basicRepositoryWithNamedEntity;
        this.nameEntity = nameEntity;
    }

    public ResponseEntity<?> findByName(String name) {
        try {
            List<T> entities = basicRepositoryWithNamedEntity.findByNameIgnoreCase(name);

            return entities.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(nameEntity+" not found with name: " + name)
                    : ResponseEntity.ok(entities);
        } catch (Exception e) {
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }

}
