// Test para la clase UserRepository
package com.example.userlogin.repositories;

import com.example.userlogin.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Test
    void findByUsername() {
        // Mockear el UserRepository
        UserRepository userRepository = mock(UserRepository.class);
        User user = new User();
        user.setUsername("testuser");

        // Definir el comportamiento del mock
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Llamar al método y verificar el resultado
        Optional<User> foundUser = userRepository.findByUsername("testuser");

        // Verificar que el User se encuentra y sus propiedades
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
    }
}