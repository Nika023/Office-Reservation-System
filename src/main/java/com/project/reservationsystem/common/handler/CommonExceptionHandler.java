package com.project.reservationsystem.common.handler;

import com.project.reservationsystem.common.exceptions.EmailIsTakenException;
import com.project.reservationsystem.common.exceptions.EmailNotValidException;
import com.project.reservationsystem.common.exceptions.PasswordNotValidException;
import com.project.reservationsystem.common.exceptions.RequestBodyUncompleteException;
import com.project.reservationsystem.common.exceptions.UsernameIsTakenException;
import com.project.reservationsystem.common.exceptions.WrongPasswordException;
import com.project.reservationsystem.common.exceptions.WrongUsernameException;
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

  @ExceptionHandler(RequestBodyUncompleteException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto uncompleteRequest() {
    return new ErrorDto("400", "Your request is not complete");
  }

  @ExceptionHandler(EmailIsTakenException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorDto emailIsTaken() {
    return new ErrorDto("409", "This email already has an account");
  }

  @ExceptionHandler(EmailNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto emailIsNotValid() {
    return new ErrorDto("400", "Please enter a valid email");
  }

  @ExceptionHandler(PasswordNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto passwordIsNotValid() {
    return new ErrorDto("400", "Please make sure that password is at least 8 characters long");
  }

  @ExceptionHandler(WrongUsernameException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorDto wrongUsername() {
    return new ErrorDto("401", "Wrong username");
  }

  @ExceptionHandler(WrongPasswordException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorDto wrongPassword() {
    return new ErrorDto("401", "Wrong password");
  }
}
