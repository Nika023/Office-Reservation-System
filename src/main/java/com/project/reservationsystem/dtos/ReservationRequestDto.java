package com.project.reservationsystem.dtos;

import java.sql.Time;

public class ReservationRequestDto {

  private String officeName;
  private Time startingTime;
  private Time endTime;

  public ReservationRequestDto(String officeName, Time startingTime, Time endTime) {
    this.officeName = officeName;
    this.startingTime = startingTime;
    this.endTime = endTime;
  }

  public ReservationRequestDto() {
  }

  public String getOfficeName() {
    return officeName;
  }

  public Time getStartingTime() {
    return startingTime;
  }

  public Time getEndTime() {
    return endTime;
  }
}
