package julian.TurnosBarber.service;

import jakarta.persistence.EntityNotFoundException;
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


    public List<BarberShop> getBarberShops(){
        List<BarberShop> barberShops = barberShopRepository.findAll();
        return barberShops;
    }

    public BarberShop findBarberShopById(String id){
        BarberShop barberShop = barberShopRepository.findById(id).orElse(null);
        return barberShop;
    }

    @Transactional
    public BarberShop saveBarberShop(BarberShop barberShop){
        return barberShopRepository.save(barberShop);
    }

    @Transactional
    public BarberShop updateBarberShop(String id, BarberShop updatedShopData) {
        return barberShopRepository.findById(id)// Busca la barberia por ID, devuelve Optional<BarberShop>
                .map(barberShop -> {// Si lo encuentra, ejecuta esta función
                    // Se actualizan los datos del barbero
                    barberShop.setName(updatedShopData.getName());
                    barberShop.setAdress(updatedShopData.getAdress());
                    barberShop.setPhone(updatedShopData.getPhone());
                    barberShop.setBarbers(updatedShopData.getBarbers());
                    barberShop.setServices(updatedShopData.getServices());
                    // Se guarda el barbero actualizado en la base de datos
                    return barberShopRepository.save(barberShop);
                })//si el id no existe ejecuta este mensaje de error
                .orElseThrow(() -> new EntityNotFoundException("Barberia no encontrada con id " + id));
    }
    @Transactional
    public void deleteBarberShop(String id){
        barberShopRepository.deleteById(id);
    }

    public List<BarberShop> getActiveBarberShop(){ //Devuelve la lista de barberos activos de la barberia
        return barberShopRepository.findByIsActiveTrue();
    }

    public List<Appointment> findByShopId(String id){ //Devuelve las reservas de a barberia
        BarberShop barberShop = barberShopRepository.findById(id).orElseThrow(() -> new RuntimeException("BarberShop no encontrada"));
        return appointmentRepository.findByShopId(barberShop.getId());
    }
}
