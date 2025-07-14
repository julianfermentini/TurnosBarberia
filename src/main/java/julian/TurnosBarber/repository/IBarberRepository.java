package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBarberRepository extends JpaRepository<Barber, String> {
    List<Barber> findByActiveTrue();
}
