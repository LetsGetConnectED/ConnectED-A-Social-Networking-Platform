package com.dxc.config;
import io.jsonwebtoken.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dxc.dto.SignUpRequest;
import com.dxc.model.User;

import java.util.Date;

@Component
public class JwtTokenProvider {
	
	@Autowired
	private SignUpRequest signuprequest;


    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;
    

    public String generateToken(Authentication authentication) {

    	 SignUpRequest signuprequest  = ( SignUpRequest) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(signuprequest.getUserid()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            // Invalid JWT signature
            // Log the exception
        } catch (MalformedJwtException ex) {
            // Invalid JWT token
            // Log the exception
        } catch (ExpiredJwtException ex) {
            // Expired JWT token
            // Log the exception
        } catch (UnsupportedJwtException ex) {
            // Unsupported JWT token
            // Log the exception
        } catch (IllegalArgumentException ex) {
            // JWT claims string is empty
            // Log the exception
        }
        return false;
    }
}
