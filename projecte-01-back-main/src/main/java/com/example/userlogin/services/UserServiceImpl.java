package com.example.userlogin.services;

import com.example.userlogin.config.SecurityConfig;
import com.example.userlogin.models.Profile;
import com.example.userlogin.models.User;
import com.example.userlogin.ports.UserPort;
import com.example.userlogin.repositories.ProfileRepository;
import com.example.userlogin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

/*
 * FUNCIONALIDAD
 * Implementación de los métodos definidos en la interfaz UserService.
 * Se encarga de realizar las operaciones de registro, búsqueda y validación de usuarios en la base de datos.
 * Además, implementa el método loadUserByUsername de la interfaz UserDetailsService para evitar una dependencia
 * cíclica con la clase SecurityConfig.
 * Se utilizan puertos en lugar de repositorios directamente para seguir el principio de la arquitectura hexagonal.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private ProfileRepository profileRepository;
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;
    private final SecurityConfig securityConfig;

    @Autowired
    // Se inyecta el puerto UserPort en el constructor de la clase
    public UserServiceImpl(UserPort userPort, PasswordEncoder passwordEncoder, SecurityConfig securityConfig) {
        this.userPort = userPort;
        this.passwordEncoder = passwordEncoder;
        this.securityConfig = securityConfig;
    }

    // Método que se encarga de cargar un usuario basándose en el username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userPort.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.get().getUsername())
                .password(user.get().getPassword())
                .build();
    }

    // Método que se encarga de registrar un usuario en la base de datos
    public User register(User user) {
        // Codificar la contraseña antes de guardarla en la base de datos
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Crear un perfil predeterminado para el usuario
        Profile defaultProfile = new Profile();
        defaultProfile.setFirstName("Default First Name");
        defaultProfile.setLastName("Default Last Name");
        defaultProfile.setUser(user);

        // Guardar el perfil predeterminado en la base de datos
        user.setProfile(defaultProfile);

        return userPort.saveUser(user);
    }

    // Método que se encarga de validar la contraseña de un usuario basándose en la pareja de información user
    // y password
    public boolean validatePassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    // Método que se encarga de encontrar un usuario basándose en el username
    public Optional<User> findByUsername(String username) {

        return userPort.findByUsername(username);
    }
}