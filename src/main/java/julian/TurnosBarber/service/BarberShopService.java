package julian.TurnosBarber.service;

import jakarta.transaction.Transactional;
import julian.TurnosBarber.entity.Appointment;
import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.entity.BarberShop;
import julian.TurnosBarber.repository.IAppointmentRepository;
import julian.TurnosBarber.repository.IBarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberShopService {
    @Autowired
    private IBarberShopRepository barberShopRepository;
    @Autowired
    private IAppointmentRepository appointmentRepository;


    private List<BarberShop> getBarberShops(){
        List<BarberShop> barberShops = barberShopRepository.findAll();
        return barberShops;
    }

    private BarberShop findBarberShopById(String id){
        BarberShop barberShop = barberShopRepository.findById(id).orElse(null);
        return barberShop;
    }

    @Transactional
    public void saveBarberShop(BarberShop barberShop){
        barberShopRepository.save(barberShop);
    }
    @Transactional
    public void deleteBarberShop(BarberShop barberShop){
        barberShopRepository.delete(barberShop);
    }

    public List<BarberShop> getActiveBarberShop(){ //Devuelve la lista de barberos activos de la barberia
        return barberShopRepository.findByIsActiveTrue();
    }

    public List<Appointment> findByShopId(String id){ //Devuelve las reservas de a barberia
        BarberShop barberShop = barberShopRepository.findById(id).orElseThrow(() -> new RuntimeException("BarberShop no encontrada"));
        return appointmentRepository.findByShopId(barberShop.getId());
    }
}
