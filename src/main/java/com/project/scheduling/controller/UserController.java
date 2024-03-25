package com.project.scheduling.controller;

import com.project.scheduling.model.UserDto;
import com.project.scheduling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestParam String name, @RequestParam String email) {
        if (userService.emailExists(email)) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        } else {
            userService.addUser(new UserDto(name, email));
            return new ResponseEntity<>("User created", HttpStatus.OK);
        }
    }

    @GetMapping("/user_meetings")
    public ResponseEntity<Map<Integer, String>> getUserMeetings(@RequestParam String email) {
        UserDto user = userService.getUserByMail(email);
        return new ResponseEntity<>(userService.getUserMeetings(user), HttpStatus.OK);
    }
}