package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.OfficeDto;
import com.project.reservationsystem.models.Office;
import com.project.reservationsystem.repositories.OfficeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfficeServiceImpl implements OfficeService{
  private OfficeRepository officeRepository;

  @Autowired
  public OfficeServiceImpl(OfficeRepository officeRepository) {
    this.officeRepository = officeRepository;
  }

  @Override
  public List<OfficeDto> listAllOffices() {
    List<OfficeDto> allOffices = new ArrayList<>();
    for(Office o : officeRepository.findAll()){
      allOffices.add(new OfficeDto(o));
    }
    return allOffices;
  }
}
