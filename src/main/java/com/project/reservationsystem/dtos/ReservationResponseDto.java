package com.project.reservationsystem.dtos;

public class ReservationResponseDto {

  private  String text;

  public ReservationResponseDto(String text) {
    this.text = text;
  }

  public ReservationResponseDto() {
  }

  public String getText() {
    return text;
  }
}
