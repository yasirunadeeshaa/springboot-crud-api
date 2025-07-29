package com.yasiru.healthcare_system.service;


import com.yasiru.healthcare_system.entity.Doctor;
import com.yasiru.healthcare_system.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    private DoctorService doctorService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        doctorService = new DoctorService(doctorRepository); // manual injection
    }

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

    @Test
    void testGetDoctorById_Found() {
        Doctor doctor = new Doctor(1L, "Dr. Alice", "Dermatology");

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        Doctor result = doctorService.getDoctorById(1L);

        assertNotNull(result);
        assertEquals("Dr. Alice", result.getName());
        assertEquals("Dermatology", result.getSpecialization());
    }

    @Test
    void testGetDoctorById_NotFound() {
        when(doctorRepository.findById(99L)).thenReturn(Optional.empty());

        Doctor result = doctorService.getDoctorById(99L);

        assertNull(result);
    }

    @Test
    void testCreateDoctor() {
        Doctor newDoctor = new Doctor(null, "Dr. Leo", "Pediatrics");
        Doctor savedDoctor = new Doctor(10L, "Dr. Leo", "Pediatrics");

        when(doctorRepository.save(newDoctor)).thenReturn(savedDoctor);

        Doctor result = doctorService.createDoctor(newDoctor);

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Dr. Leo", result.getName());
        assertEquals("Pediatrics", result.getSpecialization());
    }
}
