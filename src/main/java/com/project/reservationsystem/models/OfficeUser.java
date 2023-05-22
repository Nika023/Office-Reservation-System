package com.project.reservationsystem.models;

import com.project.reservationsystem.dtos.RegistrationDto;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class OfficeUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String password;
  private String email;

  @OneToMany
  private List<Reservation> reservationList;

  public OfficeUser(String name, String password, String email) {
    this.name = name;
    this.password = password;
    this.email = email;
  }
  public OfficeUser(RegistrationDto newUser) {
    this.name = newUser.getUsername();
    this.password = newUser.getPassword();
    this.email = newUser.getEmail();
  }

  public OfficeUser() {
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }
}
