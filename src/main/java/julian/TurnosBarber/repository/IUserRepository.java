package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String> {
    List<User> findByIsActiveTrue();
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
