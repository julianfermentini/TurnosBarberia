package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarberShopRepository extends JpaRepository<BarberShop, String> {
}
