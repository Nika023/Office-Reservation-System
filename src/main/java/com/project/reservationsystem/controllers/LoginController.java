package com.project.reservationsystem.controllers;

import com.project.reservationsystem.common.exceptions.WrongPasswordException;
import com.project.reservationsystem.dtos.LoginDto;
import com.project.reservationsystem.dtos.LoginResponseDto;
import com.project.reservationsystem.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final LoginServiceImpl loginService;

  @Autowired
  public LoginController(LoginServiceImpl loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginDto loginDto) {
    loginService.checkRequestBody(loginDto);
    loginService.checkIfUserExist(loginDto);
    try {
      loginService.authenticate(loginDto);
    } catch (BadCredentialsException e) {
      throw new WrongPasswordException();
    }
    return ResponseEntity.status(200).body(
        new LoginResponseDto("You were singed in with an access token " + loginService.getToken()));
  }
}
