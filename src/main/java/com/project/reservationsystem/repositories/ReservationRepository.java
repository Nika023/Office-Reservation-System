package com.project.reservationsystem.repositories;

import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.models.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  List<Reservation> findAllByOfficeIdAndDate(Long id, Date date);
  List<Reservation> findAllByEmployee(OfficeUser employee);
}
