package com.medic.edgigoted.controllers;

import com.medic.edgigoted.entities.Doctor;
import com.medic.edgigoted.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Endpoint to fetch all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // Endpoint to fetch a doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create a new doctor
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    // Endpoint to update an existing doctor
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id).orElse(null);
        if (existingDoctor != null) {
            updatedDoctor.setId(id);
            Doctor savedDoctor = doctorRepository.save(updatedDoctor);
            return new ResponseEntity<>(savedDoctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a doctor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        Doctor existingDoctor = doctorRepository.findById(id).orElse(null);
        if (existingDoctor != null) {
            doctorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint for doctor sign up
    @PostMapping("/signup")
    public ResponseEntity<Doctor> signUp(@RequestBody Doctor doctor) {
        // You may want to add validation logic here to ensure unique email or other requirements
        Doctor savedDoctor = doctorRepository.save(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signInDoctor(@RequestBody Doctor doctor) {
        String email = doctor.getEmail();
        String password = doctor.getPassword();
        String role = doctor.getRole(); // Get the role selected by the user

        // Retrieve doctor by email
        Doctor existingDoctor = doctorRepository.findByEmail(email);

        // Check if the doctor exists and the password matches
        if (existingDoctor != null && existingDoctor.getPassword().equals(password)) {
            // Check if the selected role matches the doctor's role
            if (role != null && role.equals("doctor")) { // Add null check for role
                // If the sign-in is successful and the role matches, return the doctor object with additional information
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("id", existingDoctor.getId());
                responseData.put("name", existingDoctor.getName());
                // Add other fields as needed

                return ResponseEntity.ok(responseData);
            } else {
                // If the selected role doesn't match the doctor's role, return UNAUTHORIZED
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid role for doctor"); // Custom error message
            }
        } else {
            // If the sign-in fails, return a custom error message along with HttpStatus.UNAUTHORIZED
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password"); // Custom error message
        }
    }


}
