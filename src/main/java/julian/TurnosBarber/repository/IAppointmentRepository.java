package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<Appointment, String> {
}
