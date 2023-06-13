package com.project.reservationsystem.controllers;

import com.project.reservationsystem.dtos.ReservationRequestDto;
import com.project.reservationsystem.models.Reservation;
import com.project.reservationsystem.services.EmailServiceImpl;
import com.project.reservationsystem.services.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

  private final ReservationServiceImpl reservationService;
  private final EmailServiceImpl emailService;

  @Autowired
  public ReservationController(ReservationServiceImpl reservationService, EmailServiceImpl emailService) {
    this.reservationService = reservationService;
    this.emailService = emailService;
  }

  @PostMapping("new-reservation")
  public ResponseEntity newReservation(@RequestBody ReservationRequestDto reservationRequestDto,
      @RequestHeader(name = "Authorization") String token) {
    reservationService.checkOfficeName(reservationRequestDto);
    reservationService.checkOpeningHours(reservationRequestDto);
    reservationService.checkAvailability(reservationRequestDto);
    reservationService.saveReservation(reservationRequestDto, token);
    emailService.sendMail(token,reservationRequestDto);
    return ResponseEntity.ok().body("Your reservation has been made. We sent you the mail with all the information.");
  }

  @GetMapping("my-reservations")
  public ResponseEntity myReservations(@RequestHeader(name = "Authorization") String token) {
    return ResponseEntity.ok().body(reservationService.reservationsByEmployee(token));
  }

  @GetMapping("reservations/{officeName}")
  public ResponseEntity reservationsByOffice(@PathVariable(name = "officeName") String officeName) {
    return ResponseEntity.ok().body(reservationService.reservationsByOffice(officeName));
  }

  @DeleteMapping("reservation/{id}")
  public ResponseEntity deleteReservation(@PathVariable(name = "id") Long id,
      @RequestHeader(name = "Authorization") String token) {
    reservationService.deleteReservation(token,id);
    return ResponseEntity.status(200).body("Reservation was deleted");
  }
}
