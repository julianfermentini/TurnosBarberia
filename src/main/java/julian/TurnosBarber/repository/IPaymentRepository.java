package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, String> {
}
