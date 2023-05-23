package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.ReservationRequestDto;

public interface ReservationService {
  void saveReservation(ReservationRequestDto reservationRequestDto, String token);

}
