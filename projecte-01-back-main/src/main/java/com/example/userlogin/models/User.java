package com.example.userlogin.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")

/* FUNCIONALIDAD
 * Esta clase representa a un usuario registrado en la aplicación.
 * Cada usuario tiene un correo electrónico, una contraseña y un nombre de usuario únicos.
 * Además, se maneja un perfil (relación uno a uno con la clase Profile) asociado al usuario, que contiene
 * información adicional, y una lista de conversaciones en las que participa el usuario.
 * */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relación con Profile
    private Profile profile;

    @ManyToMany(mappedBy = "users")
    private List<Conversation> conversations; // Lista de conversaciones en las que participa el usuario
}