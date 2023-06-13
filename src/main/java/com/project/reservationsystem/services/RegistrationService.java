package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.RegistrationDto;

public interface RegistrationService {

  void save(RegistrationDto user);

  void checkUsername(RegistrationDto user);

  void checkRequestBody(RegistrationDto user);

  void checkEmail(RegistrationDto user);

  void checkPassword(RegistrationDto user);
}
