package julian.TurnosBarber.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import julian.TurnosBarber.entity.Appointment;
import julian.TurnosBarber.entity.Barber;
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

    public List<Barber> getBarbers(){
        List<Barber> barbers = barberRepository.findAll();
        return barbers;
    }

    public Barber getBarberById(String id){
        Barber barber = barberRepository.findById(id).orElse(null);
        return barber;
    }
    @Transactional
    public Barber saveBarber(Barber barber){
        return barberRepository.save(barber);
    }
    @Transactional
    public void deleteBarber(String id){
        barberRepository.deleteById(id);
    }

    public List<Barber> getActiveBarbers(){
        return barberRepository.findByIsActiveTrue();
    }

    @Transactional
    public Barber updateBarber(String id, Barber updatedBarberData) {
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

    public List<Appointment> getBarberAppointments(String barberId) {
        Barber barber = barberRepository.findById(barberId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + barberId));

        return appointmentRepository.findByBarberId(barber.getId());
    }
}
