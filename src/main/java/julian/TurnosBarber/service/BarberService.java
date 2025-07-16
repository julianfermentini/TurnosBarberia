package julian.TurnosBarber.service;

import jakarta.persistence.EntityNotFoundException;
import julian.TurnosBarber.entity.Appointment;
import julian.TurnosBarber.entity.Barber;
import julian.TurnosBarber.entity.User;
import julian.TurnosBarber.repository.IAppointmentRepository;
import julian.TurnosBarber.repository.IBarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberService {

    @Autowired
    private IBarberRepository barberRepository;
    @Autowired
    private IAppointmentRepository appointmentRepository;

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
        return barberRepository.findByIsActiveTrue();
    }

    public User updateUser(String id, Barber updatedBarberData) {
        return barberRepository.findById(id)// Busca al barbero por ID, devuelve Optional<Barber>
                .map(barber -> {// Si lo encuentra, ejecuta esta función
                    // Se actualizan los datos del barbero
                    barber.setName(updatedBarberData.getName());
                    barber.setLastName(updatedBarberData.getLastName());
                    barber.setEmail(updatedBarberData.getEmail());
                    barber.setPhone(updatedBarberData.getPhone());
                    barber.setPassword(updatedBarberData.getPassword());
                    // Se guarda el barbero actualizado en la base de datos
                    return barberRepository.save(barber);
                })//si el id no existe ejecuta este mensaje de error
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));
    }

    public List<Appointment> getBarberAppointments(String id) {
        Barber barber = barberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));

        return appointmentRepository.findByBarberId(barber.getId());
    }
}
