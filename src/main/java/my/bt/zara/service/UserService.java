package my.bt.zara.service;

import my.bt.zara.model.User;
import my.bt.zara.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User registerUser(String username, String password) {
    // 이미 존재하는 사용자인지 확인
    if (userRepository.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already exists");
    }

    // 비밀번호 암호화
    String encodedPassword = passwordEncoder.encode(password);

    // User 객체 생성 (Lombok 빌더 패턴 사용)
    User user = User.registerUser(username, encodedPassword);

    return userRepository.save(user);
  }
}
