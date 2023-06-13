package com.project.reservationsystem.services;

import com.project.reservationsystem.common.exceptions.RequestBodyUncompleteException;
import com.project.reservationsystem.common.exceptions.WrongUsernameException;
import com.project.reservationsystem.dtos.LoginDto;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import com.project.reservationsystem.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  private final AuthenticationManager authenticationManager;
  private final JwtGenerator jwtGenerator;
  private final OfficeUserRepository officeUserRepository;

  String token = "";

  @Autowired
  public LoginServiceImpl(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator,
      OfficeUserRepository officeUserRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtGenerator = jwtGenerator;
    this.officeUserRepository = officeUserRepository;
  }

  @Override
  public void authenticate(LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    token = jwtGenerator.generateToken(authentication);
  }

  @Override
  public void checkRequestBody(LoginDto loginDto) {
    if (loginDto.getUsername() == null || loginDto.getUsername().isEmpty()
        || loginDto.getPassword() == null || loginDto.getPassword().isEmpty()) {
      throw new RequestBodyUncompleteException();
    }
  }

  @Override
  public void checkIfUserExist(LoginDto loginDto) {
    if (!officeUserRepository.findFirstByUsername(loginDto.getUsername()).isPresent()) {
      throw new WrongUsernameException();
    }
  }

  public String getToken() {
    return token;
  }

}
