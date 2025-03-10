package com.example.userlogin.ports;

import com.example.userlogin.models.User;
import com.example.userlogin.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
/*
 * FUNCIONALIDAD
 * Adaptador de la interfaz UserPort que permite la comunicación con la base de datos.
 */
public class UserJpaAdapter implements UserPort {

    private final UserRepository userRepository;

    public UserJpaAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    // Método que permite buscar un usuario por su nombre de usuario.
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    // Método para guardar la persistencia del usuario.
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

