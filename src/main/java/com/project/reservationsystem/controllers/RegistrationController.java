package com.project.reservationsystem.controllers;

import com.project.reservationsystem.dtos.RegistrationDto;
import com.project.reservationsystem.services.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

  private RegistrationServiceImpl registrationService;

  @Autowired
  public RegistrationController(RegistrationServiceImpl registrationService) {
    this.registrationService = registrationService;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody RegistrationDto registrationDto){
    registrationService.checkUsername(registrationDto);
    registrationService.save(registrationDto);
    return ResponseEntity.ok().body("Welcome onboard!");
  }
}
