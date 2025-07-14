package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.BarberService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarberServiceRepository extends JpaRepository<BarberService, String> {
}
