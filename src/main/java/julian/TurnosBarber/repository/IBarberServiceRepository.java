package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.BarberJobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarberServiceRepository extends JpaRepository<BarberJobs, String> {
}
