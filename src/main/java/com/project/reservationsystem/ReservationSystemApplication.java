package com.project.reservationsystem;

import com.project.reservationsystem.models.Office;
import com.project.reservationsystem.repositories.OfficeRepository;
import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationSystemApplication implements CommandLineRunner {

	private final OfficeRepository officeRepository;

	@Autowired
	public ReservationSystemApplication(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		officeRepository.save(new Office("O1", "First floor, big windows", new Time(8,0,0), new Time(15,0,0)));
		officeRepository.save(new Office("O2", "First floor, coffee machine inside", new Time(8,0,0), new Time(15,0,0)));
	}
}
