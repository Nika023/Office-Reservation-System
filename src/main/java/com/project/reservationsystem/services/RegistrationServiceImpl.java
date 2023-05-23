package com.project.reservationsystem.services;

import com.project.reservationsystem.common.exceptions.UsernameIsTakenException;
import com.project.reservationsystem.dtos.RegistrationDto;
import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

  private final OfficeUserRepository officeUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public RegistrationServiceImpl(OfficeUserRepository officeUserRepository, PasswordEncoder passwordEncoder) {
    this.officeUserRepository = officeUserRepository;
    this.passwordEncoder = passwordEncoder;

  }

  @Override
  public void save(RegistrationDto user) {
    officeUserRepository.save(new OfficeUser(user.getUsername(), passwordEncoder.encode(
        user.getPassword()), user.getEmail()));
  }

  @Override
  public void checkUsername(RegistrationDto user) {
    if (officeUserRepository.findFirstByUsername(user.getUsername()).isPresent()){
      throw new UsernameIsTakenException();
    }
  }
}
