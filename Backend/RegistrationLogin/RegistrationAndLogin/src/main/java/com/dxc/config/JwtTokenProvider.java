<<<<<<< HEAD
<<<<<<< HEAD




package com.dxc.config;
/*
 * import io.jsonwebtoken.*;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.stereotype.Component;
 * 
 * import java.util.Date;
 * 
 * @Component public class JwtTokenProvider {
 * 
 * @Value("${app.jwtSecret}") private String jwtSecret;
 * 
 * @Value("${app.jwtExpirationInMs}") private int jwtExpirationInMs;
 * 
 * @SuppressWarnings("deprecation") public String generateToken(String email) {
 * Date now = new Date(); Date expiryDate = new Date(now.getTime() +
 * jwtExpirationInMs);
 * 
 * return Jwts.builder() .setSubject(email) .setIssuedAt(new Date())
 * .setExpiration(expiryDate) .signWith(SignatureAlgorithm.HS512, jwtSecret)
 * .compact(); }
 * 
 * public String getEmailFromToken(String token) {
 * 
 * @SuppressWarnings("deprecation") Claims claims = Jwts.parser()
 * .setSigningKey(jwtSecret) .parseClaimsJws(token) .getBody();
 * 
 * return claims.getSubject(); }
 * 
 * @SuppressWarnings("deprecation") public boolean validateToken(String
 * authToken) { try {
 * Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken); return
 * true; } catch (SignatureException ex) {
 * System.out.println("Invalid JWT signature"); } catch (MalformedJwtException
 * ex) { System.out.println("Invalid JWT token"); } catch (ExpiredJwtException
 * ex) { System.out.println("Expired JWT token"); } catch
 * (UnsupportedJwtException ex) { System.out.println("Unsupported JWT token"); }
 * catch (IllegalArgumentException ex) {
 * System.out.println("JWT claims string is empty."); } return false; } }
 * 
 * 
 */



import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
=======
package com.dxc.config;
import io.jsonwebtoken.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dxc.dto.SignUpRequest;
import com.dxc.model.User;

>>>>>>> 2eb8b3e6121c67f01ea61d28ffc44d3247289f66
import java.util.Date;

@Component
public class JwtTokenProvider {
<<<<<<< HEAD

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    private final SecretKey secretKey;

    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret) {
        // Generate a secure random key with sufficient size for HMAC-SHA algorithm
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(String email) {
=======
	
	@Autowired
	private SignUpRequest signuprequest;


    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;
    

    public String generateToken(Authentication authentication) {

    	 SignUpRequest signuprequest  = ( SignUpRequest) authentication.getPrincipal();

>>>>>>> 2eb8b3e6121c67f01ea61d28ffc44d3247289f66
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
<<<<<<< HEAD
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
=======
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
>>>>>>> 2eb8b3e6121c67f01ea61d28ffc44d3247289f66
    }

    public boolean validateToken(String authToken) {
        try {
<<<<<<< HEAD
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
=======
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
>>>>>>> 2eb8b3e6121c67f01ea61d28ffc44d3247289f66
        }
        return false;
    }
}
=======
//package com.dxc.config;
//import io.jsonwebtoken.*;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import com.dxc.dto.SignUpRequest;
//import com.dxc.model.User;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//	@Autowired
//	private SignUpRequest signuprequest;
//
//
//    @Value("${app.jwtSecret}")
//    private String jwtSecret;
//
//    @Value("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;
//
//
//    public String generateToken(Authentication authentication) {
//
//    	 SignUpRequest signuprequest  = ( SignUpRequest) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
//
//        return Jwts.builder()
//                .setSubject(Long.toString(signuprequest.getUserid()))
//                .setIssuedAt(new Date())
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public Long getUserIdFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return Long.parseLong(claims.getSubject());
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException ex) {
//            // Invalid JWT signature
//            // Log the exception
//        } catch (MalformedJwtException ex) {
//            // Invalid JWT token
//            // Log the exception
//        } catch (ExpiredJwtException ex) {
//            // Expired JWT token
//            // Log the exception
//        } catch (UnsupportedJwtException ex) {
//            // Unsupported JWT token
//            // Log the exception
//        } catch (IllegalArgumentException ex) {
//            // JWT claims string is empty
//            // Log the exception
//        }
//        return false;
//    }
//}
>>>>>>> 48b0fbe02e87a595ab4c60fed6b71c3f43b9b453
