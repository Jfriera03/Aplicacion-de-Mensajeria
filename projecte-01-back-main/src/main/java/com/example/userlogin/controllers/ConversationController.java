// ConversationController.java
package com.example.userlogin.controllers;

import com.example.userlogin.models.Conversation;
import com.example.userlogin.services.ConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Controlador REST para gestionar las operaciones de conversación en la aplicación.
 * Proporciona endpoints para crear y recuperar conversaciones privadas y de grupo.
 *
 * Endpoints:
 * - POST /api/conversations/private-conversation: Crea una nueva conversación privada.
 * - GET /api/conversations/private-conversation: Recupera una conversación privada.
 * - POST /api/conversations/group-conversation: Crea una nueva conversación de grupo.
 * - GET /api/conversations/group-conversation: Recupera una conversación de grupo.
 */
@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    // Usamos el servicio de conversación para gestionar las operaciones de conversación.
    private static final Logger logger = LoggerFactory.getLogger(ConversationController.class); // Logger
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    // CREAR CONVERSACIÓN PRIVADA
    @PostMapping("/private-conversation")
    public ResponseEntity<Conversation> createPrivateConversation(@RequestParam String user2Username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        logger.info("Creating private conversation between {} and {}", currentUsername, user2Username);
        Conversation conversation = conversationService.createPrivateConversation(user2Username, currentUsername);
        return ResponseEntity.ok(conversation);
    }

    // RECUPERAR CONVERSACIÓN PRIVADA
    @GetMapping("/private-conversation")
    public ResponseEntity<Conversation> getPrivateConversation(@RequestParam String user2Username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        logger.info("Retrieving private conversation between {} and {}", currentUsername, user2Username);
        Conversation conversation = conversationService.getPrivateConversation(user2Username, currentUsername);
        return ResponseEntity.ok(conversation);
    }

    // CREAR CONVERSACIÓN DE GRUPO
    @PostMapping("/group-conversation")
    public ResponseEntity<Conversation> createGroupConversation(
            @RequestParam String conversationName,
            @RequestParam List<String> participants) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        logger.info("Creating group conversation '{}' with participants: {}", conversationName, participants);
        Conversation conversation = conversationService.createGroupConversation(conversationName, Set.copyOf(participants), currentUsername);
        return ResponseEntity.ok(conversation);
    }

    // RECUPERAR CONVERSACIÓN DE GRUPO
    @GetMapping("/group-conversation")
    public ResponseEntity<Conversation> getGroupConversation(@RequestParam String conversationName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        logger.info("Retrieving group conversation '{}' for user {}", conversationName, currentUsername);
        Conversation conversation = conversationService.getGroupConversation(conversationName, currentUsername);
        return ResponseEntity.ok(conversation);
    }

    @GetMapping("/my-conversations")
    public ResponseEntity<List<Conversation>> getMyConversations(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        logger.info("Fetching conversations for user {}", username);
        List<Conversation> conversations = conversationService.getConversationsByUsername(username);
        return ResponseEntity.ok(conversations);
    }
}
