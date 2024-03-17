package com.medic.edgigoted.controllers;

import com.medic.edgigoted.dtos.SalleDTO;
import com.medic.edgigoted.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salles")
public class SalleController {

    @Autowired
    private SalleService salleService;

    @PostMapping
    public ResponseEntity<SalleDTO> addSalle(@RequestBody SalleDTO salleDTO) {
        SalleDTO savedSalle = salleService.addSalle(salleDTO);
        return new ResponseEntity<>(savedSalle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalleDTO> getSalle(@PathVariable Long id) {
        SalleDTO salleDTO = salleService.getSalle(id);
        return new ResponseEntity<>(salleDTO, HttpStatus.OK);
    }

}
