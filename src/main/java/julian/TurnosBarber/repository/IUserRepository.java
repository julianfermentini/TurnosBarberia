package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, String> {
}
