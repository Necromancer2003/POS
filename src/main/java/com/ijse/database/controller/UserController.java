package com.ijse.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.database.dto.UserPwdDTO;
import com.ijse.database.entity.User;
import com.ijse.database.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/users")
    public User creatUser(@RequestBody User user) {      
        return userService.creatUser(user);
    }

    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<User> changeUserPassword(@PathVariable Long id, @RequestBody UserPwdDTO userPwdDTO) {
        
        return ResponseEntity.ok().body(userService.changeUserPassword(id, userPwdDTO));
    }
    
}
