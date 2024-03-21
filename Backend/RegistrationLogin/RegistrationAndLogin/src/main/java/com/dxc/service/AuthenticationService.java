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
	JwtAuthenticationResponse signup(SignUpRequest signUpRequest);
=======
    JwtAuthenticationResponse signin(SigninRequest signinRequest) throws InvalidCredentialsException;
//
//    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
>>>>>>> 9083e49908be6b2278f251a13d4aec9b7b969535

    JwtAuthenticationResponse adminSignin(SigninRequest signinRequest);

	JwtAuthenticationResponse generateToken(User user);
}
