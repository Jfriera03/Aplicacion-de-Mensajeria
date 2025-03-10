package com.example.userlogin.services;

import com.example.userlogin.models.Conversation;
import com.example.userlogin.models.User;
import com.example.userlogin.ports.ConversationPort;
import com.example.userlogin.ports.UserPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * En ConversationServiceImpl se utilizan puertos en lugar de repositorios directamente para seguir el principio
 * de la arquitectura hexagonal. Proporcionando una capa de abstracción entre la lógica de negocio y la
 * capa de persistencia.
 */
@Service
public class ConversationServiceImpl implements ConversationService{

    private static final Logger logger = LoggerFactory.getLogger(ConversationServiceImpl.class);
    private final ConversationPort conversationPort;
    private final UserPort userPort;

    public ConversationServiceImpl(ConversationPort conversationPort, UserPort userPort) {
        this.conversationPort = conversationPort;
        this.userPort = userPort;
    }
    // GET CONVERSACIÓN
    @Override
    public Optional<Conversation> getConversationById(Long conversationId) {
        logger.info("Fetching conversation with ID {}", conversationId);
        return conversationPort.findById(conversationId);
    }

    // VERIFICAR SI UN USUARIO PERTENECE A UNA CONVERSACIÓN
    @Override
    public boolean isUserPartOfConversation(Long conversationId, String username) {
        logger.info("Checking if user {} is part of conversation with ID {}", username, conversationId);
        // Buscar la conversación por ID
        Conversation conversation = getConversationById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        // Validar la existencia del usuario
        User user = userPort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retornar si el usuario pertenece a la conversación
        return conversation.getUsers().contains(user);
    }

    // GET CONVERSACIÓN PRIVADA
    @Override
    public Conversation getPrivateConversation(String otherUsername, String authenticatedUsername) {
        logger.info("Fetching private conversation between {} and {}", authenticatedUsername, otherUsername);
        User authenticatedUser = userPort.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        User otherUser = userPort.findByUsername(otherUsername)
                .orElseThrow(() -> new RuntimeException("Other user not found"));

        List<User> participants = List.of(authenticatedUser, otherUser);

        return conversationPort.findPrivateConversation(participants)
                .orElseThrow(() -> new RuntimeException("Private conversation not found"));
    }

    // CREAR CONVERSACIÓN PRIVADA
    @Override
    public Conversation createPrivateConversation(String otherUsername, String authenticatedUsername) {
        logger.info("Creating private conversation between {} and {}", authenticatedUsername, otherUsername);
        // Buscar al usuario autenticado que crea la conversación
        User authenticatedUser = userPort.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        // Buscar al otro usuario que participará en la conversación
        User otherUser = userPort.findByUsername(otherUsername)
                .orElseThrow(() -> new RuntimeException("Other user not found"));

        List<User> participants = List.of(authenticatedUser, otherUser);

        // Verificar si ya existe la conversación
        try {
            return getPrivateConversation(otherUsername, authenticatedUsername);
        } catch (RuntimeException e) {
            // Si no se encuentra la conversación privada, se crea una nueva
            Conversation conversation = new Conversation();
            conversation.setUsers(participants);
            return conversationPort.save(conversation);
        }
    }

    // GET CONVERSACIÓN GRUPAL
    @Override
    public Conversation getGroupConversation(String conversationName, String authenticatedUsername) {
        logger.info("Fetching group conversation '{}' for user {}", conversationName, authenticatedUsername);
        // Buscar al usuario autenticado
        User authenticatedUser = userPort.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        // Buscar la conversación grupal por nombre
        Conversation conversation = conversationPort.findByConversationName(conversationName)
                .orElseThrow(() -> new RuntimeException("Group conversation not found"));

        // Verificar si el usuario autenticado es parte de la conversación grupal
        if (!conversation.getUsers().contains(authenticatedUser)) {
            throw new RuntimeException("User is not a participant in this group conversation");
        }
        return conversation;
    }

    // CREAR CONVERSACIÓN GRUPAL
    @Override
    public Conversation createGroupConversation(String conversationName, Set<String> participants, String authenticatedUsername) {
        logger.info("Creating group conversation '{}' with participants: {}", conversationName, participants);
        User authenticatedUser = userPort.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        List<User> participantUsers = participants.stream()
                .map(username -> userPort.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found: " + username)))
                .collect(Collectors.toList());

        if (!participantUsers.contains(authenticatedUser)) {
            throw new RuntimeException("Authenticated user must be part of the group conversation");
        }

        try {
            Conversation existingConversation = getGroupConversation(conversationName, authenticatedUsername);
            if (existingConversation != null) {
                throw new RuntimeException("Group conversation with this name already exists");
            }
        } catch (RuntimeException e) {
            logger.info("Group conversation '{}' not found, proceeding to create a new one", conversationName);
        }

        Conversation conversation = new Conversation();
        conversation.setConversationName(conversationName);
        conversation.setUsers(participantUsers);
        return conversationPort.save(conversation);
    }

    @Override
    public List<Conversation> getConversationsByUsername(String username) {
        logger.info("Fetching conversations for user {}", username);
        User user = userPort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getConversations();
    }

}
