package julian.TurnosBarber.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "appointments")
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
    @JoinColumn(name = "jobs_id", nullable = false)
    private BarberJobs jobs;

    @Column(name = "shop_id", nullable = false)
    private String shopId;

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

    public String getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public Barber getBarber() {
        return barber;
    }

    public BarberJobs getJobs() {
        return jobs;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public String getCalendlyUrl() {
        return calendlyUrl;
    }

    public String getMpId() {
        return mpId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public void setJobs(BarberJobs jobs) {
        this.jobs = jobs;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public void setCalendlyUrl(String calendlyUrl) {
        this.calendlyUrl = calendlyUrl;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

