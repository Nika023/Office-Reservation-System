package com.project.reservationsystem.models;

import java.sql.Time;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "offices")
public class Office {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private Time openingTime;
  private Time closingTime;

  @OneToMany
  private List<Reservation> reservationList;

  public Office(String name, String description, Time openingTime, Time closingTime) {
    this.name = name;
    this.description = description;
    this.openingTime = openingTime;
    this.closingTime = closingTime;
  }

  public Office() {
  }

  public Long getId() {
    return id;
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
