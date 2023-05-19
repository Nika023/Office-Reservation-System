package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.OfficeDto;
import com.project.reservationsystem.models.Office;
import java.util.List;

public interface OfficeService {
  List<OfficeDto> listAllOffices();
}
