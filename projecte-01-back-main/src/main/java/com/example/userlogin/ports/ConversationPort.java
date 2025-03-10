package com.example.userlogin.ports;

import com.example.userlogin.models.Conversation;
import com.example.userlogin.models.User;

import java.util.List;
import java.util.Optional;

public interface ConversationPort {
    // Encuentra una conversación por su id
    Optional<Conversation> findById(Long conversationId);
    // Guarda una conversación
    Conversation save(Conversation conversation);
    // Encuentra una conversación privada
    Optional<Conversation> findPrivateConversation(List<User> participants);
    // Encuentra una conversación por su nombre
    Optional<Conversation> findByConversationName(String conversationName);
}
