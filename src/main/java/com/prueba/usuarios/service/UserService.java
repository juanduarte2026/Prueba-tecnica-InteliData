package com.prueba.usuarios.service;

import com.prueba.usuarios.exception.DuplicateEmailException;
import com.prueba.usuarios.exception.DuplicateIdException;
import com.prueba.usuarios.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public User registerUser(User user) {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
        
        if (emailExists(user.getEmail())) {
            throw new DuplicateEmailException("El email ya está registrado.");
        }
        
        if (idExists(user.getId())) {
            throw new DuplicateIdException("El id ya está registrado.");
        }
        
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users); 
    }

    private boolean emailExists(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    private boolean idExists(Long id) {
        return users.stream()
                .anyMatch(user -> user.getId().equals(id));
    }

    public void clearAllUsers() {
        users.clear();
    }
}