package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, String> {
    List<User> findByIsActiveTrue();

}
