package com.yasiru.healthcare_system.controller;

import com.yasiru.healthcare_system.entity.Doctor;
import com.yasiru.healthcare_system.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                 //@Controller + @ResponseBody
@RequestMapping("/api/doctors")
@Tag(name = "Doctor Management")
public class DoctorController {

    private final DoctorService service;


    public DoctorController(DoctorService service){
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "create a new doctor")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return service.createDoctor(doctor);
    }

    @GetMapping
    @Operation(summary = "Get all doctors")
    public List<Doctor> getAllDoctors() {
        return service.getAllDoctors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by ID")
    public Doctor getDoctor(@PathVariable Long id) {
        return service.getDoctorById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a doctor")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return service.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a doctor")
    public void deleteDoctor(@PathVariable Long id) {
        service.deleteDoctor(id);
    }

}
