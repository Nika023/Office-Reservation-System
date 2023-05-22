package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.RegistrationDto;
import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

  private OfficeUserRepository officeUserRepository;

  @Autowired
  public RegistrationServiceImpl(OfficeUserRepository officeUserRepository) {
    this.officeUserRepository = officeUserRepository;
  }

  @Override
  public void save(RegistrationDto user) {
    officeUserRepository.save(new OfficeUser(user));
  }
}
