package julian.TurnosBarber.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="users")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

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
    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;

}

enum Role{
    CLIENT, BARBER, ADMIN
}