package com.yasiru.healthcare_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yasiru.healthcare_system.entity.Doctor;
import com.yasiru.healthcare_system.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DoctorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Doctor savedDoctor;

    @BeforeEach
    void setUp() {
        doctorRepository.deleteAll();
        savedDoctor = doctorRepository.save(new Doctor(null, "Dr. Nuwan", "Cardiologist"));
    }

    @Test
    void testGetAllDoctors() throws Exception {
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Dr. Nuwan"));
    }

    @Test
    void testGetDoctorById() throws Exception {
        mockMvc.perform(get("/api/doctors/{id}", savedDoctor.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Nuwan"));
    }

    @Test
    void testCreateDoctor() throws Exception {
        Doctor newDoctor = new Doctor(null, "Dr. Amal", "Neurologist");

        mockMvc.perform(post("/api/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Amal"))
                .andExpect(jsonPath("$.specialization").value("Neurologist"));
    }

    @Test
    void testUpdateDoctor() throws Exception {
        Doctor updated = new Doctor(null, "Dr. Updated", "Dermatologist");

        mockMvc.perform(put("/api/doctors/{id}", savedDoctor.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Updated"))
                .andExpect(jsonPath("$.specialization").value("Dermatologist"));
    }

    @Test
    void testDeleteDoctor() throws Exception {
        mockMvc.perform(delete("/api/doctors/{id}", savedDoctor.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/doctors/{id}", savedDoctor.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("")); // null returned
    }
}
