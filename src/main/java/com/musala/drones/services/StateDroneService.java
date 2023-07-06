package com.musala.drones.services;

import com.musala.drones.entities.StateDrone;
import com.musala.drones.repositories.StateDroneRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.Optional;

@Service
public class StateDroneService extends BasicServiceWithNamedEntity<StateDrone, Long> {

    private final StateDroneRepository stateDroneRepository;
    public StateDroneService(StateDroneRepository stateDroneRepository) {
        super(stateDroneRepository, "State of Drone");
        this.stateDroneRepository = stateDroneRepository;
    }

    public StateDrone findStateDroneById(Long id){
        try{
            Optional<StateDrone> optional = stateDroneRepository.findById(id);
            return optional.get();
        }catch (Exception e){
            throw new ErrorResponseException(
                    HttpStatus.BAD_REQUEST,
                    ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage()),
                    null
            );
        }
    }
}
