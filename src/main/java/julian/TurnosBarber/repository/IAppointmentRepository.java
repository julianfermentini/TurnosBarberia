package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByBarberId(String id);
}
