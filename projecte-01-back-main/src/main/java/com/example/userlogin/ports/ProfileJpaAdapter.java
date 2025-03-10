package com.example.userlogin.ports;

import com.example.userlogin.models.Profile;
import com.example.userlogin.repositories.ProfileRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
/*
 * FUNCIONALIDAD
 * Adaptador de la interfaz ProfilePort que permite la comunicación con la base de datos.
 */
public class ProfileJpaAdapter implements ProfilePort {

    private final ProfileRepository profileRepository;

    public ProfileJpaAdapter(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    // Método que permite buscar un perfil por el id del usuario.
    public Optional<Profile> findByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

    @Override
    // Método para guardar la persistencia del perfil.
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
