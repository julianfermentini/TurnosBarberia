package julian.TurnosBarber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)
public class User {

   @Id
  @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
    private String lastName;


    private Integer phone;

    @Column(unique = true ,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();

    private boolean isActive;

}

enum Role{
    CLIENT, BARBER, ADMIN
}