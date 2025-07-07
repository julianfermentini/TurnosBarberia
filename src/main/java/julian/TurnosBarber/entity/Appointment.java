package julian.TurnosBarber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne //conexion con user
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne //conexion con barber
    @JoinColumn(name = "barber_id", nullable = false)
    private Barber barber;

    @ManyToOne //conexion con service
    @JoinColumn(name = "service_id", nullable = false)
    private BarberService service;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private Integer duration;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status = AppointmentStatus.PENDING;

    private BigDecimal totalAmount;
    private BigDecimal depositAmount;
    private String calendlyUrl;
    private String mpId;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private Payment payment;


}

enum AppointmentStatus{
    PENDING, CONFIRMED, CANCELLED, COMPLETED
}