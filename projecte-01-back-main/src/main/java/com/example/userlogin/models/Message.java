package com.example.userlogin.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation; // Relación con la conversación

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender; // Usuario que envía el mensaje

    @Column(nullable = false)
    private String content; // Contenido del mensaje

    @Column(nullable = false)
    private LocalDate sentDate = LocalDate.now(); // Fecha de envío/recibido

    @Column(nullable = false)
    private LocalTime sentTime = LocalTime.now(); // Fecha de envío/recibido
}