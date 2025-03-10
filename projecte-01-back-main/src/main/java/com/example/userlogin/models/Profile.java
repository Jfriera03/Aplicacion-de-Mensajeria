package com.example.userlogin.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "profiles")

/* FUNCIONALIDAD
 * Esta clase representa el perfil de un usuario registrado en la aplicación.
 * Cada perfil tiene un nombre, un apellido, un número de teléfono y una dirección.
 * Además, se maneja una relación uno a uno con la clase User, que representa al usuario al que pertenece el perfil.
 */
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName = "Default First Name"; // Valor por defecto

    @Column(name = "last_name", nullable = false)
    private String lastName = "Default Last Name"; // Valor por defecto

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    // user_id es la relación del perfil con un único usuario ya registrado
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relación con el usuario
}