package com.project.reservationsystem.security;

import com.project.reservationsystem.models.OfficeUser;
import com.project.reservationsystem.repositories.OfficeUserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OfficeUserDetailsService implements UserDetailsService {

  private final OfficeUserRepository officeUserRepository;

  @Autowired
  public OfficeUserDetailsService(OfficeUserRepository officeUserRepository) {
    this.officeUserRepository = officeUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    OfficeUser officeUser = officeUserRepository.findFirstByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("There is no user with this username"));
    return new User(officeUser.getUsername(), officeUser.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
  }
}
