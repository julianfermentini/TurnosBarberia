package julian.TurnosBarber.service;

import jakarta.persistence.EntityNotFoundException;
import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.entity.User;
import julian.TurnosBarber.repository.IBarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberService {

    @Autowired
    private IBarberRepository barberRepository;

    private List<Barber> getBarbers(){
        List<Barber> barbers = barberRepository.findAll();
        return barbers;
    }

    private Barber findBarberById(String id){
        Barber barber = barberRepository.findById(id).orElse(null);
        return barber;
    }

    public void saveBarber(Barber barber){
        barberRepository.save(barber);
    }

    public void deleteBarber(Barber barber){
        barberRepository.delete(barber);
    }

    public List<Barber> getActiveBarbers(){
        return barberRepository.findByActiveTrue();
    }

    public User updateUser(String id, Barber updatedBarberData) {
        return barberRepository.findById(id).map(barber -> {
                    barber.setName(updatedBarberData.getName());
                    barber.setLastName(updatedBarberData.getLastName());
                    barber.setEmail(updatedBarberData.getEmail());
                    barber.setPhone(updatedBarberData.getPhone());
                    barber.setPassword(updatedBarberData.getPassword());
                    return barberRepository.save(barber);
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));
    }
}
