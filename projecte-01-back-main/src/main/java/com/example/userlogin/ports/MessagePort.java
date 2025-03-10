package com.example.userlogin.ports;

import com.example.userlogin.models.Message;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MessagePort {
    // Encuentra un mensaje por su ID
    Optional<Message> findById(Long messageId);
    // Guarda un mensaje
    Message saveMessage(Message message);
    // Elimina un mensaje
    void deleteMessage(Message message);
    // Encuentra mensajes por ID de conversación y fecha de envío
    List<Message> findByConversationIdAndSentDate(Long conversationId, LocalDate sentDate);
}

