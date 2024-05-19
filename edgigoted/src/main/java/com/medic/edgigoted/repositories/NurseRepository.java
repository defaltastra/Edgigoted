package com.medic.edgigoted.repositories;

import com.medic.edgigoted.entities.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Nurse findByEmail(String email);
}