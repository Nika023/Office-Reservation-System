package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.LoginDto;

public interface LoginService {

  void authenticate(LoginDto loginDto);

  void checkRequestBody(LoginDto loginDto);

  void checkIfUserExist(LoginDto loginDto);
}
