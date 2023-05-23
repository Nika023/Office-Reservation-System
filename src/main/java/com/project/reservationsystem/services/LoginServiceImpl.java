package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.LoginDto;
import com.project.reservationsystem.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

  private final AuthenticationManager authenticationManager;
  private final JwtGenerator jwtGenerator;

  String token = "";
  @Autowired
  public LoginServiceImpl(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
    this.authenticationManager = authenticationManager;
    this.jwtGenerator = jwtGenerator;
  }

  @Override
  public void authenticate(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    token = jwtGenerator.generateToken(authentication);
  }

  public String getToken() {
    return token;
  }

}
