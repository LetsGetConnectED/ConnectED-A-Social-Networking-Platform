package com.dxc.service;

import com.dxc.dto.JwtAuthenticationResponse;
import com.dxc.dto.RefreshTokenRequest;
import com.dxc.dto.SignUpRequest;
import com.dxc.dto.SigninRequest;
import com.dxc.exception.InvalidCredentialsException;
import com.dxc.exception.UserExistsException;
import com.dxc.model.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest) throws UserExistsException;

<<<<<<< HEAD
    JwtAuthenticationResponse signin(SigninRequest signinRequest) throws InvalidCredentialsException;
=======
     User signup(SignUpRequest signUpRequest);
>>>>>>> 9803b956d5e3410726c9811648a72b8ca5376f2e

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    JwtAuthenticationResponse adminSignin(SigninRequest signinRequest);

	JwtAuthenticationResponse generateToken(User user);
}
