package com.dxc.util;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String generateToken() {
        // Generate a unique token using UUID or any other method
        return UUID.randomUUID().toString();
    }

    public static String encodePassword(String password) {
        return encoder.encode(password);
    }
}
