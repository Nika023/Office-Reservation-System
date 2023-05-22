package com.project.reservationsystem.dtos;

public class LoginDto {

  private String username;
  private String password;

  public LoginDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public LoginDto() {
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }
}
