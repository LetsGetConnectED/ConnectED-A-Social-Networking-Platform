//package com.dxc.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.dxc.dto.JwtAuthenticationResponse;
//import com.dxc.dto.RefreshTokenRequest;
//import com.dxc.dto.SignUpRequest;
//import com.dxc.dto.SigninRequest;
//import com.dxc.model.Role;
//import com.dxc.model.User;
//import com.dxc.repository.UserRepository;
//
//import java.util.HashMap;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService {
//    
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JWTService jwtService;
//    
//
//    
//    public User signup(SignUpRequest signUpRequest) {
//        User user = new User();
//        user.setUseremail(signUpRequest.getUseremail());
//        user.setUsername(signUpRequest.getUsername());
//        user.setRole(Role.USER);
//        user.setUserpassword(passwordEncoder.encode(signUpRequest.getUserpassword()));
//        
//        return userRepository.save(user);    
//
//        
//    }
//    
//    @Override
//    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUseremail(), signinRequest.getUserpassword()));
//
//        var user = userRepository.findByUseremail(signinRequest.getUseremail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
//        var jwt = jwtService.generateToken(user);
//        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
//
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//        jwtAuthenticationResponse.setToken(jwt);
//        jwtAuthenticationResponse.setRefreshToken(refreshToken);
//        return jwtAuthenticationResponse;
//    }
//    
//    @Override
//    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        String useremail = jwtService.extractUserName(refreshTokenRequest.getToken());
//        User user = userRepository.findByUseremail(useremail).orElseThrow();
//        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
//            var jwt = jwtService.generateToken(user);
//
//            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//            jwtAuthenticationResponse.setToken(jwt);
//            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
//            return jwtAuthenticationResponse;
//        }
//        return null;
//    }
//
//}

package com.dxc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dxc.dto.JwtAuthenticationResponse;
import com.dxc.dto.RefreshTokenRequest;
import com.dxc.dto.SignUpRequest;
import com.dxc.dto.SigninRequest;
import com.dxc.exception.InvalidCredentialsException;
import com.dxc.exception.UserExistsException;
import com.dxc.model.Role;
import com.dxc.model.User;
import com.dxc.repository.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Override
    public User signup(SignUpRequest signUpRequest) {
        String email = signUpRequest.getUseremail();
        if (userRepository.findByuseremail(email).isPresent()) {
            throw new UserExistsException("User with email " + email + " already exists");
        }

        String password = signUpRequest.getUserpassword();
        User user = new User();
        user.setUseremail(email);
        user.setUsername(signUpRequest.getUsername());
        user.setRole(Role.USER);
        user.setUserpassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        String email = signinRequest.getUseremail();
        String password = signinRequest.getUserpassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new InvalidCredentialsException("Incorrect password");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String token = jwtService.generateToken(userDetails);
//        String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername());
        
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(token);
//        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        
        System.out.println("jwtTOKEN: "+jwtAuthenticationResponse.toString());
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse adminSignin(SigninRequest signinRequest) {
        String email = signinRequest.getUseremail();
        String password = signinRequest.getUserpassword();

        if (isAdminCredentials(email, password)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String token = jwtService.generateToken(userDetails);
//            String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername());

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(token);
//            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            return jwtAuthenticationResponse;
        } else {
            throw new InvalidCredentialsException("Invalid admin credentials");
        }
    }

    private boolean isAdminCredentials(String email, String password) {
        return "connected@gmail.com".equals(email) && "connected".equals(password); 
    }

    @Override
    public JwtAuthenticationResponse generateToken(User user) {
        String token = jwtService.generateToken(user);
//        String refreshToken = jwtService.generateRefreshToken(user.getUseremail());
        
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(token);
//        response.setRefreshToken(refreshToken);
        return response;
    }

//    @Override
//    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        String username = jwtService.extractUserName(refreshTokenRequest.getToken());
//        Optional<User> user = userRepository.findByUserEmail(username);
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
//            String jwt = jwtService.generateToken(user);
//
//            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//            jwtAuthenticationResponse.setToken(jwt);
//            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
//            return jwtAuthenticationResponse;
//        }
//        return null;
//    }
//}
//    @Override
//    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        String username = jwtService.extractUserName(refreshTokenRequest.getToken());
//        Optional<User> userOptional = userRepository.findByuseremail(username);
//        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
//            String jwt = jwtService.generateToken(user);
//
//            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//            jwtAuthenticationResponse.setToken(jwt);
//            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
//            return jwtAuthenticationResponse;
//        } else {
//            throw new IllegalArgumentException("Invalid token or user");
//        }
//    }

    
//    @Override
//    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//        String refreshToken = refreshTokenRequest.getToken();
//        
//        if (jwtService.validateRefreshToken(refreshToken)) {
//            String userEmail = jwtService.extractUserEmail(refreshToken);
//            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
//            
//            String token = jwtService.generateToken(userDetails);
//            String newRefreshToken = jwtService.generateRefreshToken(userDetails);
//            
//            JwtAuthenticationResponse response = new JwtAuthenticationResponse();
//            response.setToken(token);
//            response.setRefreshToken(newRefreshToken);
//            
//            return response;
//        } else {
//            throw new IllegalArgumentException("Invalid refresh token");
//        }
//    }

}