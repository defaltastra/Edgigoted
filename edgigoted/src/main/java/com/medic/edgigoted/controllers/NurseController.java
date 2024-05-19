package com.medic.edgigoted.controllers;

import com.medic.edgigoted.entities.Nurse;
import com.medic.edgigoted.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    private final NurseRepository nurseRepository;

    @Autowired
    public NurseController(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @GetMapping
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElse(null);
        if (nurse != null) {
            return ResponseEntity.ok(nurse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Nurse> addNurse(@RequestBody Nurse nurse) {
        Nurse createdNurse = nurseRepository.save(nurse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNurse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable Long id, @RequestBody Nurse nurseDetails) {
        Nurse nurse = nurseRepository.findById(id)
                .orElse(null);
        if (nurse != null) {
            nurse.setName(nurseDetails.getName());
            nurse.setEmail(nurseDetails.getEmail());
            nurse.setPassword(nurseDetails.getPassword());
            nurse.setRole(nurseDetails.getRole());

            Nurse updatedNurse = nurseRepository.save(nurse);
            return ResponseEntity.ok(updatedNurse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElse(null);
        if (nurse != null) {
            nurseRepository.delete(nurse);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signInNurse(@RequestBody Nurse nurse) {
        String email = nurse.getEmail();
        String password = nurse.getPassword();
        String role = nurse.getRole();

        // Retrieve nurse by email
        Nurse existingNurse = nurseRepository.findByEmail(email);

        // Check if the nurse exists and the password matches
        if (existingNurse != null && existingNurse.getPassword().equals(password)) {
            // Check if the selected role matches the nurse's role
            if (role.equals("nurse")) {
                // If the sign-in is successful and the role matches, return the nurse object with additional information
                Map<String, Object> responseData = new HashMap<>();
                responseData.put("id", existingNurse.getId());
                responseData.put("name", existingNurse.getName());
                // Add other fields as needed

                return ResponseEntity.ok(responseData);
            } else {
                // If the selected role doesn't match the nurse's role, return UNAUTHORIZED
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid role for nurse"); // Custom error message
            }
        } else {
            // If the sign-in fails, return a custom error message along with HttpStatus.UNAUTHORIZED
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password"); // Custom error message
        }
    }

}
