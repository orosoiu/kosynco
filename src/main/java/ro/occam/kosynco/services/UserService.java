package ro.occam.kosynco.services;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ro.occam.kosynco.dtos.UserDto;
import ro.occam.kosynco.entities.User;
import ro.occam.kosynco.repositories.UserRepository;

import java.util.Optional;

@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;

    public boolean exists(String username) {
        return userRepository.exists(username);
    }

    public Optional<User> get(String username) {
        return userRepository.find(username);
    }

    public void createUser(UserDto userData) {
        User user = new User(userData.getUsername(), userData.getPassword(), true, false);
        userRepository.save(user);
    }
}
