package my.bt.zara.service;

import jakarta.transaction.Transactional;
import my.bt.zara.dto.UserRegistrationRequest;
import my.bt.zara.model.Users;
import my.bt.zara.model.Role;
import my.bt.zara.repository.RoleRepository;
import my.bt.zara.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public Users registerUser(UserRegistrationRequest params) {
    if (userRepository.findByUsername(params.getUsername()).isPresent()) {
      throw new RuntimeException("Username already exists");
    }

    Role userRole = roleRepository.findByName("ROLE_USER")
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


    String encodedPassword = passwordEncoder.encode(params.getPassword());

    Users users = Users.registerUser(params.getUsername(), encodedPassword, userRole);

    return userRepository.save(users);
  }
}
