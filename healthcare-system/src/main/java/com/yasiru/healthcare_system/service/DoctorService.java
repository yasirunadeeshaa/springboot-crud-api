package com.yasiru.healthcare_system.service;

import com.yasiru.healthcare_system.entity.Doctor;
import com.yasiru.healthcare_system.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository){
        this.repository = repository;
    }

    public Doctor createDoctor(Doctor doctor){
        return repository.save(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return repository.findAll();
    }

    public Doctor getDoctorById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Doctor updateDoctor(Long id , Doctor updatedDoctor){
        Doctor doctor = getDoctorById(id);
        if(doctor != null){
            doctor.setName(updatedDoctor.getName());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            return repository.save(doctor);
        }
        return null;
    }

    public void deleteDoctor(Long id){
        repository.deleteById(id);
    }
}
