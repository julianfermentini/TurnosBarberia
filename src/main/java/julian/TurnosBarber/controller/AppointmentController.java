package julian.TurnosBarber.controller;

import julian.TurnosBarber.entity.Appointment;
import julian.TurnosBarber.entity.BarberShop;
import julian.TurnosBarber.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping //GET Obtener todos las reservas
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        List<Appointment> appointments = appointmentService.getAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}") //GET Obtener  reservas por id
    public ResponseEntity<Appointment> getAppointmentsById(@PathVariable String id){
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping //GET Obtener  reservas por usuario
    public ResponseEntity<List<Appointment>> getAppointmentsByUser(@PathVariable String id){
        List<Appointment> appointments = appointmentService.getAppointmentsByUser(id);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping //GET Obtener  reservas por barbero
    public ResponseEntity<List<Appointment>> getAppointmentsByBarber(@PathVariable String id){
        List<Appointment> appointments = appointmentService.getAppointmentsByBarber(id);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping //GET Obtener  reservas por fecha
    public ResponseEntity<List<Appointment>> getAppointmentsByDate(@PathVariable LocalDateTime date){
        List<Appointment> appointments = appointmentService.getAppointmentsByDate(date);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping //GET Obtener  reservas por usuario
    public ResponseEntity<List<Appointment>> getAppointmentsByStatus(@PathVariable String status){
        List<Appointment> appointments = appointmentService.getAppointmentsByStatus(status);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping //POST crear reserva
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment){
        Appointment savedAppointment  = appointmentService.saveAppointment(appointment);
        if(savedAppointment!= null){
            return ResponseEntity.ok(savedAppointment);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @PutMapping("/{id}") //PUT actualizar reserva
    public ResponseEntity<Appointment> updateAppointment(@PathVariable String id, @RequestBody Appointment appointment){
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
        if(updatedAppointment!= null){
            return ResponseEntity.ok(updatedAppointment);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping ("{id}")//DELETE eliminar reserva
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id){
        appointmentService.deleteAppointment(id);

        return ResponseEntity.noContent().build();


    }

    // POST /api/appointments/{id}/cancel - Cancelar cita
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable String id) {
        Appointment cancelledAppointment = appointmentService.cancelAppointment(id);
        if (cancelledAppointment != null) {
            return ResponseEntity.ok(cancelledAppointment);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /api/appointments/{id}/confirm - Confirmar cita
    @PostMapping("/{id}/confirm")
    public ResponseEntity<Appointment> confirmAppointment(@PathVariable String id) {
        Appointment confirmedAppointment = appointmentService.confirmAppointment(id);
        if (confirmedAppointment != null) {
            return ResponseEntity.ok(confirmedAppointment);
        }
        return ResponseEntity.notFound().build();
    }
}
