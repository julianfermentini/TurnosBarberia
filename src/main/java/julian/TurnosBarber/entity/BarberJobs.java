package julian.TurnosBarber.entity;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "barber_jobs")

public class BarberJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;
    private Integer price;
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)  //conexion con barbershop
    @JoinColumn(name = "barberShopId" ,nullable = false)
    private BarberShop barberShop;

    @OneToMany(mappedBy = "jobs", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    private boolean isActive = true;


}
