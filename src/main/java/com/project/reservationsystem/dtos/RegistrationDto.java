package com.project.reservationsystem.dtos;

public class RegistrationDto {

  private String username;
  private String password;
  private String email;

  public RegistrationDto(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public RegistrationDto() {
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
