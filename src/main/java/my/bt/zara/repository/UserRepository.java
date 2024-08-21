package my.bt.zara.repository;

import java.util.Optional;
import my.bt.zara.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
  Optional<Users> findByUsername(String username);
}
