package julian.TurnosBarber.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getUserById(String id){
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
    @Transactional
    public User saveUser(User user){
       return  userRepository.save(user);
    }
    @Transactional
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    public List<User> getActiveUsers(){
        return userRepository.findByIsActiveTrue();
    }

    @Transactional
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
