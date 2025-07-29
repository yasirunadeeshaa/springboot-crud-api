package com.yasiru.healthcare_system.repository;

import com.yasiru.healthcare_system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
