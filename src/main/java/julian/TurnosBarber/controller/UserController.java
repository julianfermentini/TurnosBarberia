package julian.TurnosBarber.controller;


import julian.TurnosBarber.entity.User;
import julian.TurnosBarber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping //GET Obtener todos los usuarios
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

   @GetMapping("/{id}") //GET Obtener usuario por id
    public ResponseEntity<User> getUserById(@PathVariable String id){
        User user  = userService.getUserById(id);
        if(user!= null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @PostMapping //POST crear usuario
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser  = userService.saveUser(user);
        if(savedUser!= null){
            return ResponseEntity.ok(savedUser);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @PutMapping("/{id}") //PUT actualizar usuario
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user){
        User updatedUser  = userService.updateUser(id, user);
        if(updatedUser!= null){
            return ResponseEntity.ok(updatedUser);
        }else{
            return ResponseEntity.notFound().build();

        }

    }

    @DeleteMapping ("{id}")//DELETE eliminar usuario
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteUser(id);

            return ResponseEntity.noContent().build();


    }



    @GetMapping //GET buscar por usuarios activos
    public ResponseEntity<List<User>> getActiveUsers(){
        List<User> activeUsers = userService.getActiveUsers();

            return ResponseEntity.ok(activeUsers);


    }


}
