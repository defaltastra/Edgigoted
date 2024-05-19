package com.medic.edgigoted.controllers;

import com.medic.edgigoted.entities.Admin;
import com.medic.edgigoted.entities.Doctor;
import com.medic.edgigoted.entities.Nurse;
import com.medic.edgigoted.repositories.AdminRepository;
import com.medic.edgigoted.repositories.DoctorRepository;
import com.medic.edgigoted.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository; // Assuming you have a repository for Admin entities

    @Autowired
    private DoctorRepository doctorRepository; // Assuming you have a repository for Doctor entities

    @Autowired
    private NurseRepository nurseRepository; // Assuming you have a repository for Nurse entities

    // Sign-in endpoint for Admin
    @PostMapping("/signin")
    public ResponseEntity<String> signInAdmin(@RequestBody AdminSignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();

        // Retrieve admin by email
        Admin admin = adminRepository.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {
            // Authentication successful
            return ResponseEntity.ok("Authentication successful");
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    // Endpoint for creating a Doctor
    @PostMapping("/doctors")
    public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor) {
        // Save the doctor entity to the database
        doctorRepository.save(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Doctor created successfully");
    }

    // Endpoint for creating a Nurse
    @PostMapping("/nurses")
    public ResponseEntity<String> createNurse(@RequestBody Nurse nurse) {
        // Save the nurse entity to the database
        nurseRepository.save(nurse);
        return ResponseEntity.status(HttpStatus.CREATED).body("Nurse created successfully");
    }
}
