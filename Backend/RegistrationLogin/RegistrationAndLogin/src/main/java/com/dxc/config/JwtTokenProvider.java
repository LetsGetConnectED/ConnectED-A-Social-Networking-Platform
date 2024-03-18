



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
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    private final SecretKey secretKey;

    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret) {
        // Generate a secure random key with sufficient size for HMAC-SHA algorithm
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
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
    }

    public boolean validateToken(String authToken) {
        try {
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
        }
        return false;
    }
}
