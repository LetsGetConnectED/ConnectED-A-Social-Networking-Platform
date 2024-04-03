package com.dxc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
    }
     @GetMapping("/role/{useremail}")
    public ResponseEntity<?> getrole(@PathVariable("useremail") String useremail) {
        // Assuming AuthenticationService has a method to retrieve user roles by ID
        Role userRole = ur.getrole(useremail);
        if (userRole != null) {
            return ResponseEntity.ok(userRole);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User role not found");
        }
}
    
}
