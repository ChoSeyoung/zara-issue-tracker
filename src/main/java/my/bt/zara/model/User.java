package my.bt.zara.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;
  private String password;
  private String roles;

  // 사용자 등록 메서드 (빌더 패턴 사용)
  public static User registerUser(String username, String encodedPassword) {
    return User.builder()
          .username(username)
          .password(encodedPassword)
          .roles("USER")
          .build();
  }
}
