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
  private String username;
  private String password;
  private String email;

  @OneToMany
  private List<Reservation> reservationList;

  public OfficeUser(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public OfficeUser(RegistrationDto newUser) {
    this.username = newUser.getUsername();
    this.password = newUser.getPassword();
    this.email = newUser.getEmail();
  }

  public OfficeUser() {
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }
}
