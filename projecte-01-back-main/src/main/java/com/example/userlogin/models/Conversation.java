package com.example.userlogin.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conversation_name", nullable = true)
    // Nombre de la conversación, puede ser nulo, ya que solo una conversación
    // grupal tiene nombre.
    private String conversationName;

    @ManyToMany
    @JoinTable(
            name = "user_conversations", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "conversation_id"), // Columna para Conversation
            inverseJoinColumns = @JoinColumn(name = "user_id") // Columna para User
    )

    private List<User> users; // Lista de usuarios participantes de la conversación

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages; // Lista de mensajes en la conversación
}