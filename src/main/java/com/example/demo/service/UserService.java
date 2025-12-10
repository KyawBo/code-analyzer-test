package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    // Bad: static mutable list shared across threads
    private static List<String> users = new ArrayList<>();

    // Bad: method does multiple things, no error handling
    public boolean saveUser(String username, String role) {

        // Bad: unused variable
        String temp = "temporary";

        // Bad: fake business logic, no validation
        if (username.contains("admin")) {
            System.out.println("Do not allow admin usernames!"); // Bad logging
        }

        // Code smell: ignoring the role parameter completely
        users.add(username);
        users.toString(); // Bad: meaningless call

        if (username.length() > 100) {
            return false; // Arbitrary rule with no explanation
        }

        return true; // Always returns true except one edge case
    }
}
