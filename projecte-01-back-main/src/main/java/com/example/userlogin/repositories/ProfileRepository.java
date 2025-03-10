package com.example.userlogin.repositories;

import com.example.userlogin.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

/*
 * FUNCIONALIDAD
 * Interfaz que extiende de JpaRepository para poder realizar operaciones sobre el contenido de la tabla Profile
 * de la base de datos.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    // Encuentra un perfil basado en el user_id
    @Query("SELECT p FROM Profile p WHERE p.user.id = :userId")
    Optional<Profile> findByUserId(@Param("userId") Long userId);
}