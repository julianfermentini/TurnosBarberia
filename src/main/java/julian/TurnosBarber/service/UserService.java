package julian.TurnosBarber.service;

import jakarta.persistence.EntityNotFoundException;
import julian.TurnosBarber.entity.User;
import julian.TurnosBarber.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    private List<User> getClients(){
        List<User> clientes = userRepository.findAll();
        return clientes;
    }

    private User findUserById(String id){
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> getActiveUsers(){
        return userRepository.findByActiveTrue();
    }


    public User updateUser(String id, User updatedUserData) {
        return userRepository.findById(id).map(user -> {
                    user.setName(updatedUserData.getName());
                    user.setLastName(updatedUserData.getLastName());
                    user.setEmail(updatedUserData.getEmail());
                    user.setPhone(updatedUserData.getPhone());
                    user.setPassword(updatedUserData.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));
    }
}
