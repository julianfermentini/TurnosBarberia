package julian.TurnosBarber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Entity
@Table(name = "payment")@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne //conexion con appointment
    @JoinColumn(name= "appointment")
    private Appointment appointment;

    private String mercadoPagoId;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private String paymentMethod;
    private LocalDateTime date;


}
enum Status {
    PENDING, APPROBED, REJECTED, CANCELLED
        }
