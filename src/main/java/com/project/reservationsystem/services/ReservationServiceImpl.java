package com.project.reservationsystem.services;

import com.project.reservationsystem.common.exceptions.InvalidOfficeName;
import com.project.reservationsystem.common.exceptions.TimeConflictException;
import com.project.reservationsystem.common.exceptions.TimeOutOfOpeningTimeException;
import com.project.reservationsystem.common.exceptions.WrongTimeFormatException;
import com.project.reservationsystem.dtos.ReservationRequestDto;
import com.project.reservationsystem.models.Office;
import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.models.Reservation;
import com.project.reservationsystem.repositories.OfficeRepository;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import com.project.reservationsystem.repositories.ReservationRepository;
import com.project.reservationsystem.security.JwtGenerator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

  private final ReservationRepository reservationRepository;
  private final JwtGenerator jwtGenerator;
  private final OfficeUserRepository officeUserRepository;
  private final OfficeRepository officeRepository;

  @Autowired
  public ReservationServiceImpl(ReservationRepository reservationRepository,
      JwtGenerator jwtGenerator,
      OfficeUserRepository officeUserRepository, OfficeRepository officeRepository) {
    this.reservationRepository = reservationRepository;
    this.jwtGenerator = jwtGenerator;
    this.officeUserRepository = officeUserRepository;
    this.officeRepository = officeRepository;
  }

  @Override
  public void saveReservation(ReservationRequestDto reservationRequestDto, String token) {
    OfficeUser user = officeUserRepository.findFirstByUsername(
        jwtGenerator.getUsernameFromJWT(token.substring(7))).get();
    Reservation reservation = new Reservation(reservationRequestDto.getStartingTime(),
        reservationRequestDto.getEndTime(), user,
        officeRepository.findFirstByName(reservationRequestDto.getOfficeName()),
        reservationRequestDto.getDate());
    reservationRepository.save(reservation);
  }

  public void checkOfficeName(ReservationRequestDto reservationRequestDto) {
    if (officeRepository.findFirstByName(reservationRequestDto.getOfficeName()) == null) {
      throw new InvalidOfficeName();
    }
  }

  public void checkAvailability(ReservationRequestDto reservationRequestDto) {
    Long id = officeRepository.findFirstByName(reservationRequestDto.getOfficeName()).getId();
    List<Reservation> reservationOnSameOfficeSameDay = reservationRepository.findAllByOfficeIdAndDate(
        id, reservationRequestDto.getDate());
    for (Reservation reservation : reservationOnSameOfficeSameDay) {
      if (reservation.getStartingTime().before(reservationRequestDto.getEndTime()) &&
          reservation.getEndingTime().after(reservationRequestDto.getStartingTime())) {
        throw new TimeConflictException();
      }
    }
  }


  public void checkOpeningHours(ReservationRequestDto reservationRequestDto) {
    if(reservationRequestDto.getEndTime().before(reservationRequestDto.getStartingTime())){
      throw new WrongTimeFormatException();
    }
    Office office = officeRepository.findFirstByName(reservationRequestDto.getOfficeName());
    if (reservationRequestDto.getStartingTime().before(office.getOpeningTime())
        || reservationRequestDto.getEndTime().after(office.getClosingTime())) {
      throw new TimeOutOfOpeningTimeException();
    }
  }
}
