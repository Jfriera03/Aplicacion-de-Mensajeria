package com.example.userlogin.repositories;

import com.example.userlogin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
 * FUNCIONALIDAD
 * Interfaz que extiende de JpaRepository para poder realizar operaciones sobre el contenido de la tabla User
 * de la base de datos.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    // Método que permite buscar un usuario por su nombre de usuario.
    Optional<User> findByUsername(String username);
}
