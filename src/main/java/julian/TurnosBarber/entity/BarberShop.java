package julian.TurnosBarber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "barbershops")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BarberShop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String adress;
    private Integer phone;
    private boolean isActive = true;

    @OneToMany(mappedBy = "barberShop", cascade = CascadeType.ALL)
    private List<Barber> barbers = new ArrayList<>();

    @OneToMany(mappedBy = "barberShop", cascade = CascadeType.ALL)
    private List<BarberService> services = new ArrayList<>();

}
