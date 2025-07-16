package julian.TurnosBarber.repository;

import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.entity.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBarberShopRepository extends JpaRepository<BarberShop, String> {
    List<BarberShop> findByIsActiveTrue();


}
