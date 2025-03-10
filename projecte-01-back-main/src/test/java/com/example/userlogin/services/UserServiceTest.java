package com.example.userlogin.services;

import com.example.userlogin.models.User;
import com.example.userlogin.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        // Inicializar los mocks antes de cada prueba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Crear una instancia de User y establecer sus propiedades
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("test@example.com");

        // Definir el comportamiento del mock para el método save
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Llamar al método register y verificar el resultado
        User registeredUser = userService.register(user);

        // Verificar que el usuario registrado no sea nulo y sus propiedades
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
    }

    @Test
    void testFindByUsername() {
        // Crear una instancia de User y establecer su nombre de usuario
        User user = new User();
        user.setUsername("testuser");

        // Definir el comportamiento del mock para el método findByUsername
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Llamar al método findByUsername y verificar el resultado
        Optional<User> foundUser = userService.findByUsername("testuser");

        // Verificar que el usuario encontrado esté presente y sus propiedades
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }

    @Test
    void testValidatePassword() {
        // Crear una instancia de User y establecer su contraseña
        User user = new User();
        user.setPassword("password");

        // Llamar al método validatePassword y verificar el resultado
        boolean isValid = userService.validatePassword(user, "password");

        // Verificar que la contraseña sea válida
        assertTrue(isValid);
    }
}