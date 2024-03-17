package com.medic.edgigoted.services;

import com.medic.edgigoted.dtos.SalleDTO;
import com.medic.edgigoted.entities.Salle;
import com.medic.edgigoted.repositories.SalleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
public class SalleService {

    private final SalleRepository salleRepository;

    @Autowired
    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    @Transactional
    public SalleDTO addSalle(SalleDTO salleDTO) {
        Salle salle = new Salle();
        BeanUtils.copyProperties(salleDTO, salle);
        salle = salleRepository.save(salle);
        return convertToDTO(salle);
    }

    public SalleDTO getSalle(Long id) {
        Optional<Salle> optionalSalle = salleRepository.findById(id);
        if (optionalSalle.isPresent()) {
            return convertToDTO(optionalSalle.get());
        } else {
            throw new RuntimeException("Salle not found with id: " + id);
        }
    }

    private SalleDTO convertToDTO(Salle salle) {
        SalleDTO salleDTO = new SalleDTO();
        BeanUtils.copyProperties(salle, salleDTO);
        return salleDTO;
    }
}
