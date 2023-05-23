package com.project.reservationsystem.common.handler;

import com.project.reservationsystem.common.exceptions.UsernameIsTakenException;
import com.project.reservationsystem.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(UsernameIsTakenException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorDto takenUsername() {
    return new ErrorDto("409", "This username is already taken");
  }
}
