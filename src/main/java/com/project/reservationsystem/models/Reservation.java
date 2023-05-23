package com.project.reservationsystem.models;

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

  @ManyToOne
  private OfficeUser employee;
  @ManyToOne
  private Office office;

  public Reservation(Time startingTime, Time endingTime, OfficeUser employee,
      Office office) {
    this.startingTime = startingTime;
    this.endingTime = endingTime;
    this.employee = employee;
    this.office = office;
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
}
