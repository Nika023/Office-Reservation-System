package com.project.reservationsystem.controllers;

import com.project.reservationsystem.dtos.ReservationRequestDto;
import com.project.reservationsystem.models.Reservation;
import com.project.reservationsystem.services.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

  private final ReservationServiceImpl reservationService;

  @Autowired
  public ReservationController(ReservationServiceImpl reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping ("new-reservation")
  public ResponseEntity testing(@RequestBody ReservationRequestDto reservationRequestDto, @RequestHeader(name = "Authorization") String token){
    reservationService.saveReservation(reservationRequestDto,token);
    return ResponseEntity.ok().body("Working");
  }
}
