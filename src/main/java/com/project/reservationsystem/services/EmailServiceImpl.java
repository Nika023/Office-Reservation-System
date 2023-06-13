package com.project.reservationsystem.services;

import com.project.reservationsystem.common.exceptions.EmailNotSentException;
import com.project.reservationsystem.dtos.ReservationRequestDto;
import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.models.Reservation;
import com.project.reservationsystem.repositories.OfficeRepository;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import com.project.reservationsystem.security.JwtGenerator;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;
  private final OfficeUserRepository officeUserRepository;
  private final JwtGenerator jwtGenerator;
  private final OfficeRepository officeRepository;

  @Autowired
  public EmailServiceImpl(JavaMailSender mailSender, OfficeUserRepository officeUserRepository,
      JwtGenerator jwtGenerator, OfficeRepository officeRepository) {
    this.mailSender = mailSender;
    this.jwtGenerator = jwtGenerator;
    this.officeUserRepository = officeUserRepository;
    this.officeRepository = officeRepository;
  }

  public void sendMail(String token, ReservationRequestDto reservationRequestDto) {
    OfficeUser employee = officeUserRepository.findFirstByUsername(
        jwtGenerator.getUsernameFromJWT(token.substring(7))).get();
    Reservation reservation = new Reservation(reservationRequestDto.getStartingTime(),
        reservationRequestDto.getEndTime(), employee,
        officeRepository.findFirstByName(reservationRequestDto.getOfficeName()),
        reservationRequestDto.getDate());
    try {
      String toAddress = employee.getEmail();
      String fromAddress = "reservationsysteminfo@gmail.com";
      String senderName = "Reservation System";
      String subject = "Your reservation has been made!";
      String content = "<p style=\\\"font-family: Arial, sans-serif;\\\">Your reservation was successfully made!<br><br>"
          + "On day: " + reservation.getDate().toString() + "<br>"
          + "Between " + reservation.getStartingTime().toString() + " and " + reservation.getEndingTime().toString()
          + "<br>"
          + "In office: " + reservation.getOffice().getName() + "<br>"
          + "Under the name " + reservation.getEmployee().getUsername() + "<br><br>"
          + "Thank you,<br>"
          + "Your Reservation System</p>";

      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);

      helper.setFrom(fromAddress, senderName);
      helper.setTo(toAddress);
      helper.setSubject(subject);
      helper.setText(content, true);

      mailSender.send(message);
    } catch (Exception e) {
      throw new EmailNotSentException();
    }

  }
}
