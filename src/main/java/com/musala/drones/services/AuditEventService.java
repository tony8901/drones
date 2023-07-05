package com.musala.drones.services;

import com.musala.drones.entities.AuditEvent;
import com.musala.drones.repositories.AuditEventRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditEventService extends BasicServices<AuditEvent, Long>{

    public AuditEventService(AuditEventRepository auditEventRepository){
        super(auditEventRepository, "Audit Event");
    }
}
