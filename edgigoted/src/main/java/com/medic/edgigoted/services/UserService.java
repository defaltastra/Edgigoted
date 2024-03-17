package com.medic.edgigoted.services;

import com.medic.edgigoted.dtos.UserDTO;
import com.medic.edgigoted.entities.Role;
import com.medic.edgigoted.entities.User;
import com.medic.edgigoted.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Assuming roles are represented as Strings in DTO
        Set<Role> roles = userDTO.getRoles().stream()
                .map(roleName -> new Role(roleName))
                .collect(Collectors.toSet());
        newUser.setRoles(roles);

        User savedUser = userRepository.save(newUser);
        return convertToDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return convertToDTO(optionalUser.get());
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    private UserDTO convertToDTO(User user) {
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        return new UserDTO(user.getId(), user.getEmail(), roles);
    }
}
