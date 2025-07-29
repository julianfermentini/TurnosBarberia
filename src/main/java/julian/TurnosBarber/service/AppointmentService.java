package julian.TurnosBarber.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import julian.TurnosBarber.entity.*;
import julian.TurnosBarber.repository.IAppointmentRepository;
import julian.TurnosBarber.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IUserRepository userRepository;

    public List<Appointment> getAppointments(){
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    public Appointment getAppointmentById(String id){
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        return appointment;
    }

    @Transactional
    public Appointment saveAppointment(Appointment appointment){
       return  appointmentRepository.save(appointment);
    }

    @Transactional
    public void deleteAppointment(String id){
        appointmentRepository.deleteById(id);
    }

    @Transactional
    public Appointment updateAppointment(String id, Appointment updatedAppointmentData) {
        return appointmentRepository.findById(id)// Busca la reserva por ID, devuelve Optional<Appointment>
                .map(appointment -> {// Si lo encuentra, ejecuta esta función
                    // Se actualizan los datos de la reserva
                    appointment.setClient(updatedAppointmentData.getClient());
                    appointment.setBarber(updatedAppointmentData.getBarber());
                    appointment.setJobs(updatedAppointmentData.getJobs());
                    appointment.setDateTime(updatedAppointmentData.getDateTime());
                    appointment.setStatus(updatedAppointmentData.getStatus());
                    appointment.setDepositAmount(updatedAppointmentData.getDepositAmount());
                    appointment.setTotalAmount(updatedAppointmentData.getTotalAmount());
                    // Se guarda la reserva actualizada en la base de datos
                    return appointmentRepository.save(appointment);
                })//si el id no existe ejecuta este mensaje de error
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id " + id));
    }

    public List<Appointment> getAppointmentsByUser(String id){ //Devuelve las reservas de cada usuario
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("BarberShop no encontrada"));
        return appointmentRepository.findByClientId(user.getId());
    }

    public List<Appointment> getAppointmentsByBarber(String barberId) { //Devuelve las reservas de cada barbero
        return appointmentRepository.findByBarberId(barberId);
    }

    public List<Appointment> getAppointmentsByDate(LocalDateTime date){ //Devuelve las reservas por fecha
        return appointmentRepository.findByDate(date);
    }

    public List<Appointment> getAppointmentsByStatus(String status){ //Devuelve las reservas por status
        return appointmentRepository.findByStatus(status);
    }

    @Transactional
    public Appointment cancelAppointment(String id){ //Devuelve  la reserva que desea cancelar
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la reserva con id: " +id));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment confirmAppointment(String id){ //Devuelve  la reserva que desea confirmar
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro la reserva con id: " +id));
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        return appointmentRepository.save(appointment);
    }
}
