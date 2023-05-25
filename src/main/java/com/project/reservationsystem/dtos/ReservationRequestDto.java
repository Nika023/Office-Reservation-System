package com.project.reservationsystem.dtos;

import java.sql.Date;
import java.sql.Time;

public class ReservationRequestDto {

  private String officeName;
  private Time startingTime;
  private Time endTime;
  private Date date;

  public ReservationRequestDto(String officeName, Time startingTime, Time endTime, Date date) {
    this.officeName = officeName;
    this.startingTime = startingTime;
    this.endTime = endTime;
    this.date = date;
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

  public Date getDate() {
    return date;
  }
}
