package julian.TurnosBarber.service;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import julian.TurnosBarber.entity.*;
import julian.TurnosBarber.repository.IAppointmentRepository;
import julian.TurnosBarber.repository.IPaymentRepository;
import julian.TurnosBarber.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAppointmentRepository appointmentRepository;



    public List<Payment> getPayment(){
        List<Payment> Payments = paymentRepository.findAll();
        return Payments;
    }

    public Payment findPaymentById(String id){
        Payment payment =paymentRepository.findById(id).orElse(null);
        return payment;
    }
    @Transactional
    public Payment savePayment(Payment payment){
       return paymentRepository.save(payment);
    }
    @Transactional
    public void deletePayment(String id){
        paymentRepository.deleteById(id);
    }

    @Transactional
    public Payment updatePayment(String id, Payment updatedPaymentData) {
        return paymentRepository.findById(id)// Busca el pago  por ID, devuelve Optional<Payment>
                .map(payment -> {// Si lo encuentra, ejecuta esta función
                    // Se actualizan los datos del pago
                    payment.setAppointment(updatedPaymentData.getAppointment());
                    payment.setAmount(updatedPaymentData.getAmount());
                    payment.setStatus(updatedPaymentData.getStatus());
                    payment.setPaymentMethod(updatedPaymentData.getPaymentMethod());
                    payment.setDate(updatedPaymentData.getDate());
                    // Se guarda el PAGO actualizado en la base de datos
                    return paymentRepository.save(payment);
                })//si el id no existe ejecuta este mensaje de error
                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con id " + id));
    }

    public List<Payment> getPaymentsByUser(String id){ //Devuelve los pagos por usuario
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return paymentRepository.findByAppointment_Client_Id(user.getId());
    }

    public List<Payment> getPaymentsByAppointment(String id){ //Devuelve los pagos por reserva
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return paymentRepository.findByAppointment_Id(appointment.getId());
    }

    public List<Payment> getPaymentsByStatus(PaymentStatus status){ //Devuelve los pagos por status
        return paymentRepository.findByStatus(status);
    }

    public Payment processPayment(String appointmentId, PaymentMethod method, double amount){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(()->new RuntimeException("Reserva no encontrada"));

        Payment payment = new Payment();
        payment.setAppointment(appointment);
        payment.setPaymentMethod(method);
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.APPROBED);
        payment.setDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public Double getTotalEarningsByBarber(String barberId){
        List <Appointment> appointments = appointmentRepository.findByBarberId(barberId);

        double totalAmout = 0.0;

        for(Appointment appointment : appointments){
            List<Payment> payments = paymentRepository.findByAppointment_Id(appointment.getId());
            for(Payment payment: payments){
                totalAmout += payment.getAmount();
            }
        }
        return totalAmout;
    }

    public Double getTotalEarningsByShop(String shopId){
        List <Appointment> appointments = appointmentRepository.findByShopId(shopId);

        double totalAmout = 0.0;

        for(Appointment appointment : appointments){
            List<Payment> payments = paymentRepository.findByAppointment_Id(appointment.getId());
            for(Payment payment: payments){
                totalAmout += payment.getAmount();
            }
        }
        return totalAmout;
    }


}
