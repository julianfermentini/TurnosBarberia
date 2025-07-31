package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.Appointment;
import julian.TurnosBarber.entity.Payment;
import julian.TurnosBarber.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByAppointment_Client_Id(String clientId);

    List<Payment> findByAppointment_Id(String id);

    List<Payment> findByStatus(PaymentStatus status);
}
