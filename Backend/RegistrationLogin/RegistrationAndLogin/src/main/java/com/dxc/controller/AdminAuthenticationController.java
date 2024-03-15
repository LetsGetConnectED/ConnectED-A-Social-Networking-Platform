//package com.dxc.controller;
//
//import com.dxc.dto.JwtAuthenticationResponse;
//import com.dxc.dto.SigninRequest;
//import com.dxc.service.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/admin/auth") 
//public class AdminAuthenticationController {
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    private static final String ADMIN_EMAIL = "connected@gmail.com";
//    private static final String ADMIN_PASSWORD = "connected";
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
//        try {
//            
//            if (ADMIN_EMAIL.equals(signinRequest.getUseremail()) && ADMIN_PASSWORD.equals(signinRequest.getUserpassword())) {
//                JwtAuthenticationResponse response = authenticationService.adminSignin(signinRequest);
//                return ResponseEntity.ok(response);
//            } else {
//                throw new Exception("Invalid admin credentials");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
//}
