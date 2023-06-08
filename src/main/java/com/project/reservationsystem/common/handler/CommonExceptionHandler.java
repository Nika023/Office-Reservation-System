package com.project.reservationsystem.common.handler;

import com.project.reservationsystem.common.exceptions.EmailIsTakenException;
import com.project.reservationsystem.common.exceptions.EmailNotValidException;
import com.project.reservationsystem.common.exceptions.InvalidIdException;
import com.project.reservationsystem.common.exceptions.InvalidOfficeName;
import com.project.reservationsystem.common.exceptions.NotAuthorizedForActionException;
import com.project.reservationsystem.common.exceptions.PasswordNotValidException;
import com.project.reservationsystem.common.exceptions.RequestBodyUncompleteException;
import com.project.reservationsystem.common.exceptions.TimeConflictException;
import com.project.reservationsystem.common.exceptions.TimeOutOfOpeningTimeException;
import com.project.reservationsystem.common.exceptions.UsernameIsTakenException;
import com.project.reservationsystem.common.exceptions.WrongPasswordException;
import com.project.reservationsystem.common.exceptions.WrongTimeFormatException;
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

  @ExceptionHandler(TimeConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorDto timeConflict() {
    return new ErrorDto("409",
        "Your reservation is in conflict with another reservation, please change the time or date of your reservation");
  }

  @ExceptionHandler(InvalidOfficeName.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto invalidOfficeName() {
    return new ErrorDto("400", "This office is not in database");
  }

  @ExceptionHandler(TimeOutOfOpeningTimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto timeslotNotInOpeningHours() {
    return new ErrorDto("400", "Your reservation time is not during opening hours");
  }

  @ExceptionHandler(WrongTimeFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto timeFormatIsWrong() {
    return new ErrorDto("400", "Time you provided id not set correctly");
  }

  @ExceptionHandler(InvalidIdException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto invalidId() {
    return new ErrorDto("400", "This ID is not in database.");
  }

  @ExceptionHandler(NotAuthorizedForActionException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorDto notAuthorized() {
    return new ErrorDto("401", "You are not authorized to perform this action.");
  }
}
