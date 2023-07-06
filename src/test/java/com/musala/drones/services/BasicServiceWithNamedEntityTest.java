package com.musala.drones.services;

import com.musala.drones.entities.Medication;
import com.musala.drones.repositories.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BasicServiceWithNamedEntityTest {

    @Mock
    private MedicationRepository medicationRepository;

    private MedicationService medicationService;

    @BeforeEach
    public void setUp() {
        medicationService = new MedicationService(medicationRepository);
    }

    @Test
    public void testSave() {
        Medication entity = new Medication();
        entity.setName("Test");
        entity.setCode("CODE_TEST1");
        entity.setWeight(123);

        when(medicationRepository.save(entity)).thenReturn(entity);

        ResponseEntity<?> response = medicationService.save(entity);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Medication savedEntity = (Medication) response.getBody();
        assertNotNull(savedEntity);
        assertEquals("Test", savedEntity.getName());
        assertEquals("CODE_TEST1", savedEntity.getCode());
        assertEquals(123, savedEntity.getWeight());

        verify(medicationRepository).save(entity);
    }

    @Test
    public void testFindAll() {
        // Crea una lista de entidades para devolver
        List<Medication> entities = new ArrayList<>();
        Medication entity1 = new Medication();
        entity1.setName("Test");
        entity1.setCode("CODE_TEST");
        entity1.setWeight(123);
        Medication entity2 = new Medication();
        entity2.setName("Test2");
        entity2.setCode("CODE_TEST2");
        entity2.setWeight(123);
        Medication entity3 = new Medication();
        entity3.setName("Test3");
        entity3.setCode("CODE_TEST3");
        entity3.setWeight(123);
        entities.add(entity1);
        entities.add(entity2);
        entities.add(entity3);

        when(medicationRepository.findAll()).thenReturn(entities);

        ResponseEntity<?> response = medicationService.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Medication> foundEntities = (List<Medication>) response.getBody();
        assertNotNull(foundEntities);
        assertEquals(3, foundEntities.size());
    }

    @Test
    public void testFindById() {
        Medication entity = new Medication();
        entity.setName("Test");
        entity.setCode("CODE_TEST");
        entity.setWeight(123);
        entity.setId(9658L);

        when(medicationRepository.existsById(9658L)).thenReturn(true);
        when(medicationRepository.findById(9658L)).thenReturn(Optional.of(entity));

        ResponseEntity<?> response = medicationService.findById(9658L);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Optional<Medication> foundEntity = (Optional<Medication>) response.getBody();
        assertNotNull(foundEntity);
        assertEquals(9658L, foundEntity.get().getId());
    }

    @Test
    public void testDeleteById() {
        when(medicationRepository.existsById(9658L)).thenReturn(true);

        ResponseEntity<?> response = medicationService.deleteById(9658L);

       assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(medicationRepository).deleteById(9658L);
    }
}

