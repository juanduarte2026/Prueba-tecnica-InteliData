package com.prueba.usuarios.controller;

import com.prueba.usuarios.exception.DuplicateEmailException;
import com.prueba.usuarios.exception.DuplicateIdException;
import com.prueba.usuarios.model.User;
import com.prueba.usuarios.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        
        try {
            userService.registerUser(user);
            response.put("message", "Usuario registrado exitosamente.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (DuplicateIdException | DuplicateEmailException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> clearUsers() {
        userService.clearAllUsers();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Todos los usuarios han sido eliminados.");
        return ResponseEntity.ok(response);
    }
}