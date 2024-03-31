package com.medic.edgigoted.controllers;


import com.medic.edgigoted.entities.Patient;
import com.medic.edgigoted.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        // Update patient details
        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setAddress(patientDetails.getAddress());
        patient.setPhone(patientDetails.getPhone());
        patient.setEmail(patientDetails.getEmail());

        // Save the updated patient
        Patient updatedPatient = patientRepository.save(patient);

        return ResponseEntity.ok(updatedPatient);
    }


    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patientRepository.delete(patient);
    }

    // Endpoint for patient sign-up
    @PostMapping("/signup")
    public ResponseEntity<Patient> signUp(@RequestBody Patient patient) {
        // Check if the email is already registered
        Patient existingPatient = patientRepository.findByEmail(patient.getEmail());
        if (existingPatient != null) {
            return ResponseEntity.badRequest().body(existingPatient);
        }

        // Save the new patient
        Patient savedPatient = patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    // Endpoint for patient sign-in
    @PostMapping("/signin")
    public ResponseEntity<Patient> signIn(@RequestBody Patient patient) {
        // Find the patient by email
        Patient existingPatient = patientRepository.findByEmail(patient.getEmail());
        if (existingPatient == null) {
            return ResponseEntity.notFound().build();
        }

        // Check if the passwords match
        if (!existingPatient.getPassword().equals(patient.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Patient sign-in successful
        return ResponseEntity.ok(existingPatient);
    }
}
