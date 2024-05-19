package com.medic.edgigoted.controllers;

import com.medic.edgigoted.entities.Doctor;
import com.medic.edgigoted.entities.Nurse;
import com.medic.edgigoted.repositories.DoctorRepository;
import com.medic.edgigoted.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private NurseRepository nurseRepository;

    @PostMapping("/api/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        String email = signInRequest.getEmail();
        String password = signInRequest.getPassword();
        String role = signInRequest.getRole();

        if ("doctor".equals(role)) {
            // Retrieve doctor by email
            Doctor existingDoctor = doctorRepository.findByEmail(email);

            // Check if the doctor exists and the password matches
            if (existingDoctor != null && existingDoctor.getPassword().equals(password)) {
                // If sign-in is successful, return the doctor object
                return ResponseEntity.ok(existingDoctor);
            }
        } else if ("nurse".equals(role)) {
            // Retrieve nurse by email
            Nurse existingNurse = nurseRepository.findByEmail(email);

            // Check if the nurse exists and the password matches
            if (existingNurse != null && existingNurse.getPassword().equals(password)) {
                // If sign-in is successful, return the nurse object
                return ResponseEntity.ok(existingNurse);
            }
        }

        // If sign-in fails, return a custom error message with HttpStatus.UNAUTHORIZED
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid email, password, or role");
    }
}
