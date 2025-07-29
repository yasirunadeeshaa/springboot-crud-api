package com.yasiru.healthcare_system.service;


import com.yasiru.healthcare_system.entity.Doctor;
import com.yasiru.healthcare_system.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @Test
    void testGetAllDoctors() {
        List<Doctor> mockDoctors = List.of(
                new Doctor(1L, "Dr. Smith", "Cardiology"),
                new Doctor(2L, "Dr. John", "Neurology")
        );

        when(doctorRepository.findAll()).thenReturn(mockDoctors);

        List<Doctor> doctors = doctorService.getAllDoctors();

        assertEquals(2, doctors.size());
        assertEquals("Dr. Smith", doctors.get(0).getName());
    }
}
