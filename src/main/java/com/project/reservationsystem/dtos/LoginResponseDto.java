package com.project.reservationsystem.dtos;

public class LoginResponseDto {

  private String text;

  public LoginResponseDto(String text) {
    this.text = text;
  }

  public LoginResponseDto() {
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
