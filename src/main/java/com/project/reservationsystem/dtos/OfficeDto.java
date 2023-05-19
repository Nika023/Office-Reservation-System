package com.project.reservationsystem.dtos;

import com.project.reservationsystem.models.Office;
import java.sql.Time;

public class OfficeDto {

  private String name;
  private String description;
  private Time openingTime;
  private Time closingTime;

  public OfficeDto(Office office) {
    this.name = office.getName();
    this.description = office.getDescription();
    this.openingTime = office.getOpeningTime();
    this.closingTime = office.getClosingTime();
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Time getOpeningTime() {
    return openingTime;
  }

  public Time getClosingTime() {
    return closingTime;
  }
}
