package com.project.reservationsystem.services;

import com.project.reservationsystem.common.exceptions.EmailIsTakenException;
import com.project.reservationsystem.common.exceptions.EmailNotValidException;
import com.project.reservationsystem.common.exceptions.PasswordNotValidException;
import com.project.reservationsystem.common.exceptions.RequestBodyUncompleteException;
import com.project.reservationsystem.common.exceptions.UsernameIsTakenException;
import com.project.reservationsystem.dtos.RegistrationDto;
import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

  private final OfficeUserRepository officeUserRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public RegistrationServiceImpl(OfficeUserRepository officeUserRepository,
      PasswordEncoder passwordEncoder) {
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
    if (officeUserRepository.findFirstByUsername(user.getUsername()).isPresent()) {
      throw new UsernameIsTakenException();
    }
  }

  @Override
  public void checkRequestBody(RegistrationDto user) {
    if (user.getUsername() == null || user.getUsername().isEmpty()
        || user.getPassword() == null || user.getPassword().isEmpty()
        || user.getEmail() == null || user.getEmail().isEmpty()) {
      throw new RequestBodyUncompleteException();
    }
  }

  @Override
  public void checkEmail(RegistrationDto user) {

    if (officeUserRepository.existsByEmail(user.getEmail())) {
      throw new EmailIsTakenException();
    } else {
      String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
          + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
      boolean emailIsValid = Pattern.compile(regexPattern)
          .matcher(user.getEmail())
          .matches();
      if (!emailIsValid) {
        throw new EmailNotValidException();
      }
    }
  }

  @Override
  public void checkPassword(RegistrationDto user) {
    if (user.getPassword().length() < 8) {
      throw new PasswordNotValidException();
    }
  }
}
