package com.example.userlogin.services;

import com.example.userlogin.models.Message;

import java.time.LocalDate;
import java.util.List;

/*
    * La interfaz MessagingService define la lógica de negocio para gestionar los mensajes de la aplicación.
    * Incluye métodos para enviar mensajes, eliminar mensajes y obtener mensajes de una conversación.
 */
public interface MessagingService {

    Message sendMessage(Long conversationId, String authenticatedUsername, String content);

    void deleteMessageById(Long messageId, String username);

    List<Message> getMessagesByConversation(Long conversationId, LocalDate sentDate);

}