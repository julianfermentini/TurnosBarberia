package julian.TurnosBarber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "barbers")@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Barber extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @ManyToOne //conexion con barber shop
    @JoinColumn(name = "barbershop_id", nullable = false)
    private BarberShop barberShop;


    private String calendlyUrl;
    private boolean isActive;
    private BigDecimal comission;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

}
