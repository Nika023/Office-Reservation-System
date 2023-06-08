package com.project.reservationsystem.models;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Time startingTime;
  private Time endingTime;
  private Date date;

  @ManyToOne
  private OfficeUser employee;
  @ManyToOne
  private Office office;

  public Reservation(Time startingTime, Time endingTime, OfficeUser employee, Office office,
      Date date) {
    this.startingTime = startingTime;
    this.endingTime = endingTime;
    this.employee = employee;
    this.office = office;
    this.date = date;
  }

  public Reservation() {
  }

  public Long getId() {
    return id;
  }

  public Time getStartingTime() {
    return startingTime;
  }

  public Time getEndingTime() {
    return endingTime;
  }

  public Date getDate() {
    return date;
  }

  public OfficeUser getEmployee() {
    return employee;
  }

  public Office getOffice() {
    return office;
  }
}
