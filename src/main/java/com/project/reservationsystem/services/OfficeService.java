package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.OfficeDto;
import java.util.List;

public interface OfficeService {

  List<OfficeDto> listAllOffices();

  OfficeDto showOneOffice(Long id);

  boolean checkIfExist(Long id);
}
