package com.example.userlogin.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/*
    * La clase MessageReadReceipt representa un registro de un mensaje leído por un usuario.
    * Si un mensaje tiene un registro asociado, querrá decir que el usuario ha leído el mensaje.
 */

@Data
@Entity
@Table(name = "message_read_receipts")
public class MessageReadReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id", nullable = false)
    private Message message; // id del mensaje leído

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // id del usuario que leyó el mensaje

    @Column(nullable = false)
    private LocalDateTime readTimestamp = LocalDateTime.now(); // Fecha y hora de lectura
}
