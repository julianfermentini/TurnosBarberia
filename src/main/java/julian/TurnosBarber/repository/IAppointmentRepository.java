package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByBarberId(String barberId);

    List<Appointment> findByShopId(String id);

    List<Appointment> findByClientId(String clientId);

    List<Appointment> findByDateTime(LocalDateTime date);

    List<Appointment> findByStatus(String status);

}
