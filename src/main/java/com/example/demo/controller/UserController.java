package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // Bad: unnecessary static variable
    private static int counter = 0;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Bad: method name not meaningful, mixed responsibilities, no validation
    @PostMapping("/createUser")
    public HashMap<String, Object> doEverything(@RequestBody HashMap<String, Object> input) {

        // Code smell: hardcoded value
        String defaultRole = "ADMIN";

        // Code smell: doing business logic in controller
        String username = (String) input.get("username");
        if (username == null || username.length() < 3) {
            return null; // Bad: returning null response
        }

        // Code smell: unnecessary instantiation
        HashMap<String, Object> response = new HashMap<>();
        counter++; // Bad static state modification

        // Code smell: duplicate logic
        String message = "User " + username + " created with role " + defaultRole;
        String message2 = "User " + username + " created with role " + defaultRole;

        System.out.println(message2); // Bad: using System.out instead of logging

        // Bad: calls service but ignores return value
        userService.saveUser(username, defaultRole);

        // Bad: leaking internal logic in API response
        response.put("username", username);
        response.put("role", defaultRole);
        response.put("counter", counter);
        response.put("internalMessage", message2);

        return response;
    }
}
