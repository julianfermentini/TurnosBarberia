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
    private List<BarberJobs> services = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Barber> getBarbers() {
        return barbers;
    }

    public void setBarbers(List<Barber> barbers) {
        this.barbers = barbers;
    }

    public List<BarberJobs> getServices() {
        return services;
    }

    public void setServices(List<BarberJobs> services) {
        this.services = services;
    }
}
