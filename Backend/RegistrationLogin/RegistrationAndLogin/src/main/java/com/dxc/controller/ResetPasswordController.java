package com.dxc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ResetPasswordController {

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token) {
        // Logic to handle the reset password request
        return "Password reset page";
    }
}