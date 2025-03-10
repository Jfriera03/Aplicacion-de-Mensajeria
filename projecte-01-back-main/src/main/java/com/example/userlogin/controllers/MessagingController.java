// MessagingController.java
package com.example.userlogin.controllers;

import com.example.userlogin.models.Message;
import com.example.userlogin.services.ConversationServiceImpl;
import com.example.userlogin.services.MessagingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para gestionar las operaciones de mensajería en la aplicación.
 * Proporciona endpoints para enviar, eliminar y marcar mensajes como leídos,
 * así como para recuperar mensajes de una conversación específica.
 *
 * Endpoints:
 * - POST /api/messaging/message: Envía un nuevo mensaje en una conversación.
 * - GET /api/messaging/conversation/{conversationId}/messages: Recupera los mensajes de una conversación en una fecha específica.
 * - DELETE /api/messaging/message/{messageId}: Elimina un mensaje por su ID.
 * - POST /api/messaging/mark-as-read/{messageId}: Marca un mensaje como leído.
 */
@RestController
@RequestMapping("/api/messaging")
public class MessagingController {
    private static final Logger logger = LoggerFactory.getLogger(MessagingController.class);
    // Usamos los servicios de mensajería y conversación para gestionar las operaciones de mensajería.
    private final MessagingServiceImpl messagingService;
    private final ConversationServiceImpl conversationService;

    public MessagingController(MessagingServiceImpl messagingService, ConversationServiceImpl conversationService) {
        this.messagingService = messagingService;
        this.conversationService = conversationService;
    }

    // ENVIAR MENSAJE
    @PostMapping("/message")
    public ResponseEntity<Message> sendMessage(
            @RequestParam Long conversationId,
            @RequestParam String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Message message = messagingService.sendMessage(conversationId, currentUsername, content);
        return ResponseEntity.ok(message);
    }

    // RECUPERAR MENSAJES DE UNA CONVERSACIÓN
    @GetMapping("/conversation/{conversationId}/messages")
    public ResponseEntity<List<Message>> getMessagesByConversation(
            @PathVariable Long conversationId, @RequestParam LocalDate sentDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Comprobamos si el usuario actual es parte de la conversación.
        if (!conversationService.isUserPartOfConversation(conversationId, currentUsername)) {
            return ResponseEntity.status(403).body(null);
        }
        // Recuperamos los mensajes de la conversación en la fecha especificada.
        List<Message> messagesConversation = messagingService.getMessagesByConversation(conversationId, sentDate);
        if (messagesConversation.isEmpty()) {
            throw new RuntimeException("Conversation not found");
        }
        return ResponseEntity.ok().body(messagesConversation);
    }

    // ELIMINAR MENSAJE
    @DeleteMapping("/message/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long messageId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        messagingService.deleteMessageById(messageId, currentUsername);
        return ResponseEntity.ok("Message with ID " + messageId + " has been deleted successfully.");
    }

    // MARCAR MENSAJE COMO LEÍDO
    @PostMapping("/mark-as-read/{messageId}")
    public ResponseEntity<String> markMessageAsRead(@PathVariable Long messageId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        logger.info("Marking message with ID {} as read by user {}", messageId, currentUsername);
        messagingService.markMessageAsRead(messageId, currentUsername);
        return ResponseEntity.ok("Message with ID " + messageId + " has been marked as read.");
    }
}