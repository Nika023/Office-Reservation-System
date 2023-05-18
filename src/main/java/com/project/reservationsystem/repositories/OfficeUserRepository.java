package com.project.reservationsystem.repositories;

import com.project.reservationsystem.models.OfficeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeUserRepository extends JpaRepository<OfficeUser, Long> {

}
