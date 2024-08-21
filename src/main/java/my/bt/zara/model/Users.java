package my.bt.zara.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
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
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
        name = "roles",
        joinColumns = @JoinColumn(name = "id"),
        inverseJoinColumns = @JoinColumn(name = "role")
  )
  private Set<Role> roles = new HashSet<>();

  // 사용자 등록 메서드 (빌더 패턴 사용)
  public static Users registerUser(String username, String encodedPassword, Role userRole) {
    if (username == null || username.isEmpty()) {
      throw new IllegalArgumentException("Username cannot be null or empty");
    }

    if (encodedPassword == null || encodedPassword.isEmpty()) {
      throw new IllegalArgumentException("Password cannot be null or empty");
    }

    Set<Role> roles = new HashSet<>();
    roles.add(userRole);

    return Users.builder()
          .username(username)
          .password(encodedPassword)
          .roles(roles)
          .build();
  }
}
