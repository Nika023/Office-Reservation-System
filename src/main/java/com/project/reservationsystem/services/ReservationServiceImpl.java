package com.project.reservationsystem.services;

import com.project.reservationsystem.dtos.ReservationRequestDto;
import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.models.Reservation;
import com.project.reservationsystem.repositories.OfficeRepository;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import com.project.reservationsystem.repositories.ReservationRepository;
import com.project.reservationsystem.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepository reservationRepository;
  private final JwtGenerator jwtGenerator;
  private final OfficeUserRepository officeUserRepository;
  private final OfficeRepository officeRepository;

  @Autowired
  public ReservationServiceImpl(ReservationRepository reservationRepository, JwtGenerator jwtGenerator,
      OfficeUserRepository officeUserRepository, OfficeRepository officeRepository) {
    this.reservationRepository = reservationRepository;
    this.jwtGenerator = jwtGenerator;
    this.officeUserRepository = officeUserRepository;
    this.officeRepository = officeRepository;
  }

  @Override
  public void saveReservation(ReservationRequestDto reservationRequestDto, String token) {
    OfficeUser user = officeUserRepository.findFirstByUsername(jwtGenerator.getUsernameFromJWT(token.substring(7))).get();
    Reservation reservation = new Reservation(reservationRequestDto.getStartingTime(), reservationRequestDto.getEndTime(), user, officeRepository.findFirstByName(reservationRequestDto.getOfficeName()));
    reservationRepository.save(reservation);
  }
}
