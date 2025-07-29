package julian.TurnosBarber.controller;

import julian.TurnosBarber.entity.Payment;
import julian.TurnosBarber.entity.PaymentMethod;
import julian.TurnosBarber.entity.PaymentStatus;
import julian.TurnosBarber.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // GET /api/payments - Obtener todos los pagos
    @GetMapping
    public ResponseEntity<List<Payment>> getPayments() {
        List<Payment> payments = paymentService.getPayment();
        return ResponseEntity.ok(payments);
    }

    // GET /api/payments/{id} - Obtener pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String id) {
        Payment payment = paymentService.findPaymentById(id);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        }
        return ResponseEntity.notFound().build();
    }

    // POST /api/payments - Crear nuevo pago
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment savedPayment = paymentService.savePayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    // PUT /api/payments/{id} - Actualizar pago
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable String id, @RequestBody Payment payment) {
        Payment updatedPayment = paymentService.updatePayment(id, payment);
        if (updatedPayment != null) {
            return ResponseEntity.ok(updatedPayment);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /api/payments/{id} - Eliminar pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/payments/user/{userId} - Obtener pagos por usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable String userId) {
        List<Payment> payments = paymentService.getPaymentsByUser(userId);
        return ResponseEntity.ok(payments);
    }

    // GET /api/payments/appointment/{appointmentId} - Obtener pagos por cita
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<Payment>> getPaymentsByAppointment(@PathVariable String appointmentId) {
        List<Payment> payments = paymentService.getPaymentsByAppointment(appointmentId);
        return ResponseEntity.ok(payments);
    }

    // GET /api/payments/status/{status} - Obtener pagos por estado
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@PathVariable PaymentStatus status, @PathVariable String id) {
        List<Payment> payments = paymentService.getPaymentsByStatus(status, id);
        return ResponseEntity.ok(payments);
    }



    // POST /api/payments/process - Procesar pago
    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(
            @RequestParam String appointmentId,
            @RequestParam PaymentMethod method,
            @RequestParam Double amount) {
        Payment processedPayment = paymentService.processPayment(appointmentId, method, amount);
        if (processedPayment != null) {
            return ResponseEntity.ok(processedPayment);
        }
        return ResponseEntity.badRequest().build();
    }


    // GET /api/payments/earnings/barber/{barberId} - Ganancias por barbero
    @GetMapping("/earnings/barber/{barberId}")
    public ResponseEntity<Double> getTotalEarningsByBarber(@PathVariable String barberId) {
        Double earnings = paymentService.getTotalEarningsByBarber(barberId);
        return ResponseEntity.ok(earnings);
    }

    // GET /api/payments/earnings/shop/{shopId} - Ganancias por barbería
    @GetMapping("/earnings/shop/{shopId}")
    public ResponseEntity<Double> getTotalEarningsByShop(@PathVariable String shopId) {
        Double earnings = paymentService.getTotalEarningsByShop(shopId);
        return ResponseEntity.ok(earnings);
    }


    
}
