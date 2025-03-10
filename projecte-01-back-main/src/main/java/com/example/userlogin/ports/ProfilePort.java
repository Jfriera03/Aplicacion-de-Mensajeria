package com.example.userlogin.ports;

import com.example.userlogin.models.Profile;

import java.util.Optional;

/*
 * FUNCIONALIDAD
 * Interfaz que define los métodos que se pueden realizar sobre la entidad Profile.
 * El uso de este puerto permite desacoplar la lógica de negocio de la lógica de persistencia en lo relacionado
 * con la entidad Profile de la base de datos.
 */
public interface ProfilePort {
    // Método que permite buscar un perfil por el id del usuario.
    Optional<Profile> findByUserId(Long userId);

    // Método para guardar la persistencia del perfil.
    Profile saveProfile(Profile profile);
}

