package my.bt.zara.service;

import java.util.*;
import java.util.stream.Collectors;
import my.bt.zara.model.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import my.bt.zara.model.Users;
import my.bt.zara.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || username.isEmpty()) {
      System.out.println("Received empty username!");
    } else {
      System.out.println("Received username: " + username);
    }

    Users users = userRepository.findByUsername(username).orElseThrow(() ->
          new UsernameNotFoundException("User not found with username: " + username));

    List<GrantedAuthority> authorities = users.getRoles().stream()
          .map(role -> new SimpleGrantedAuthority(role.getName()))
          .collect(Collectors.toList());

    return new CustomUserDetails(users.getId(), users.getUsername(), users.getPassword(), authorities);
  }
}
