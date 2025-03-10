package com.example.userlogin.services;

import com.example.userlogin.models.Profile;
import com.example.userlogin.ports.ProfilePort;
import com.example.userlogin.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
/*
 * FUNCIONALIDAD
 * Implementación de los métodos definidos en la interfaz ProfileService.
 * Se encarga de realizar las operaciones de búsqueda y actualización de perfiles en la base de datos.
 * Se utilizan puertos en lugar de repositorios directamente para seguir el principio de la arquitectura hexagonal.
 */
public class ProfileServiceImpl implements ProfileService {

    private final ProfilePort profilePort;
    private final UserRepository userRepository;

    // Se inyecta el puerto ProfilePort en el constructor de la clase
    public ProfileServiceImpl(ProfilePort profilePort, UserRepository userRepository) {
        this.profilePort = profilePort;
        this.userRepository = userRepository;
    }

    @Override
    // Método que se encarga de buscar un perfil basándose en el username
    public Optional<Profile> getProfileByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> profilePort.findByUserId(user.getId()));
    }

    @Override
    // Método que se encarga de actualizar un perfil basándose en el username
    public Optional<Profile> updateProfileByUsername(String username, Profile updatedProfile) {
        return userRepository.findByUsername(username).map(user -> {
            // Buscar el perfil asociado al usuario autenticado
            Profile existingProfile = profilePort.findByUserId(user.getId())
                    .orElseThrow(() -> new RuntimeException("Profile not found"));

            // Actualizar los datos del perfil
            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
            existingProfile.setPhoneNumber(updatedProfile.getPhoneNumber());
            existingProfile.setAddress(updatedProfile.getAddress());

            // Guardar y devolver el perfil actualizado usando ProfilePort
            return profilePort.saveProfile(existingProfile);
        });
    }
}
