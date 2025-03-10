package com.example.userlogin.services;

import com.example.userlogin.models.Profile;

import java.util.Optional;

/*
 * FUNCIONALIDAD
 * Interfaz que define los métodos que se pueden realizar sobre la tabla Profile de la base de datos.
 */
public interface ProfileService {

    // Encuentra un perfil basado en el username del usuario autenticado
    Optional<Profile> getProfileByUsername(String username);

    // Actualiza un perfil basándose en el username del usuario autenticado
    Optional<Profile> updateProfileByUsername(String username, Profile updatedProfile);
}

