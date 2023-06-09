package com.project.reservationsystem.controllers;

import com.project.reservationsystem.dtos.OfficeDto;
import com.project.reservationsystem.services.OfficeServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfficeController {

  private OfficeServiceImpl officeService;

  @Autowired
  public OfficeController(OfficeServiceImpl officeService) {
    this.officeService = officeService;
  }

  @GetMapping("/offices")
  public ResponseEntity<List<OfficeDto>> allOffices() {
    return ResponseEntity.status(200).body(officeService.listAllOffices());
  }

  @GetMapping("/office/{id}")
  public ResponseEntity showWantedOffice(@PathVariable(name = "id") Long id) {
    if (officeService.checkIfExist(id)) {
      return ResponseEntity.status(200).body(officeService.showOneOffice(id));
    } else {
      return ResponseEntity.status(404).body("Invalid id");
    }
  }
}
