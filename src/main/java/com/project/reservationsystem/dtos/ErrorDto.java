package com.project.reservationsystem.dtos;

public class ErrorDto {

  private String statusCode;
  private String message;

  public ErrorDto(String statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public ErrorDto() {
  }

  public String getStatusCode() {
    return statusCode;
  }

  public String getMessage() {
    return message;
  }
}
