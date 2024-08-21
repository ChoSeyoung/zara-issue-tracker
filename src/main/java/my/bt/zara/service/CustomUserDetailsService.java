package my.bt.zara.service;

import java.util.Optional;
import my.bt.zara.model.Users;
import my.bt.zara.repository.UserRepository;
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
    Users users = userRepository.findByUsername(username).orElseThrow(() ->
          new UsernameNotFoundException("User not found with username: " + username));

    return User.builder()
          .username(users.getUsername())
          .password(users.getPassword())
          .roles(users.getRole())
          .build();
  }
}
