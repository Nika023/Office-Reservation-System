package com.project.reservationsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

  private final Environment env;

  @Autowired
  public JwtGenerator(Environment env) {
    this.env = env;
  }

  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); //valid for 24 hours

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, env.getProperty("JWT_SECRET_KEY"))
        .compact();
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parser().setSigningKey(env.getProperty("JWT_SECRET_KEY")).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      throw new AuthenticationCredentialsNotFoundException("JWT is expired or incorrect");
    }
  }

  public String getUsernameFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(env.getProperty("JWT_SECRET_KEY"))
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }
}
