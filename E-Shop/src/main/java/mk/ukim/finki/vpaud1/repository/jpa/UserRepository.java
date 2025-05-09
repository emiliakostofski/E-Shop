package mk.ukim.finki.vpaud1.repository.jpa;

import mk.ukim.finki.vpaud1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
   public Optional<User> findByUsernameAndPassword(String username, String password);
   public Optional<User> findByUsername(String username);
}
