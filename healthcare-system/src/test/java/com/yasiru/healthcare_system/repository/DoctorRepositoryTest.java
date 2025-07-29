package com.yasiru.healthcare_system.repository;


import com.yasiru.healthcare_system.entity.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void testSaveAndFindDoctor() {
        Doctor doctor = new Doctor("Dr. Adams", "Pediatrics");
        Doctor saved = doctorRepository.save(doctor);

        assertNotNull(saved.getId());
        assertEquals("Dr. Adams", saved.getName());
    }
}
