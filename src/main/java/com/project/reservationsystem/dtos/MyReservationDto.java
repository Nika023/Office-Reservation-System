package com.project.reservationsystem.dtos;

import com.project.reservationsystem.models.Reservation;
import java.sql.Date;
import java.sql.Time;

public class MyReservationDto {
  private Long id;
  private String officeName;
  private Time startingTime;
  private Time endTime;
  private Date date;

  public MyReservationDto(Reservation reservation) {
    this.id = reservation.getId();
    this.officeName = reservation.getOffice().getName();
    this.startingTime = reservation.getStartingTime();
    this.endTime = reservation.getEndingTime();
    this.date = reservation.getDate();
  }

  public MyReservationDto() {
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

  public Long getId() {
    return id;
  }
}
