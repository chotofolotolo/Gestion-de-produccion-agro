package com.myproyect.miproyect.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproyect.miproyect.User;
import com.myproyect.miproyect.UserService;

@RestController
@RequestMapping("/cultivos")
public class CultivoController {
     private final CultivoService cultivoService;

    public CultivoController(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    @GetMapping("/getById")
    public ResponseEntity<User> getUserByEmail(@RequestBody String email) {
        User locatedUser = this.userService.getUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(locatedUser);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<String> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll().toString());
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
