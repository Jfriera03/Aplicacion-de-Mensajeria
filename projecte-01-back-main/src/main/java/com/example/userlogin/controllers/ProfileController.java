package com.example.userlogin.controllers;

import com.example.userlogin.models.Profile;
import com.example.userlogin.services.ProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
/*
 * FUNCIONALIDAD
 * Controlador para la gestión de los perfiles de usuario.
 * Aquí se definen las rutas y métodos necesarios para obtener y editar los perfiles de usuario siempre comprobando
 * que el usuario autenticado sea el propietario del perfil que se intenta editar.
 */
public class ProfileController {

    private final ProfileServiceImpl profileService;

    public ProfileController(ProfileServiceImpl profileService) {

        this.profileService = profileService;
    }

    // Obtener el perfil del usuario autenticado
    @GetMapping("/me")
    public ResponseEntity<Profile> getOwnProfile() {
        // Obtener el nombre de usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // Extraer el nombre de usuario desde el token

        // Llamar al servicio para obtener el perfil del usuario autenticado
        return profileService.getProfileByUsername(currentUsername)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null)); // 404 si no se encuentra el perfil
    }

    // Actualizar el perfil del usuario autenticado
    @PutMapping("/me")
    public ResponseEntity<?> updateOwnProfile(@RequestBody Profile updatedProfile) {
        // Obtener el usuario autenticado desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Llamar al servicio para actualizar el perfil
        Optional<Profile> updated = profileService.updateProfileByUsername(currentUsername, updatedProfile);

        if (updated.isPresent()) {
            return ResponseEntity.ok(updated.get());
        } else {
            return ResponseEntity.status(403).body("Unauthorized to edit this profile");
        }
    }
}