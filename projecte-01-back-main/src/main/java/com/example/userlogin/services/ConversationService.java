package com.example.userlogin.services;

import com.example.userlogin.models.Conversation;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 * La interfaz ConversationService define la lógica de negocio para gestionar las conversaciones de la aplicación.
 */
public interface ConversationService {
    // Verificar si un usuario pertenece a una conversación
    boolean isUserPartOfConversation(Long conversationId, String username);

    Optional<Conversation> getConversationById(Long conversationId);

    Conversation createPrivateConversation(String otherUsername, String authenticatedUsername);

    Conversation getPrivateConversation(String otherUsername, String authenticatedUsername);

    Conversation createGroupConversation(String conversationName, Set<String> participants, String authenticatedUsername);

    Conversation getGroupConversation(String conversationName, String authenticatedUsername);

    List<Conversation> getConversationsByUsername(String username);

}
