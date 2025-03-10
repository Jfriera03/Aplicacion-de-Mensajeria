package com.example.userlogin.ports;

import com.example.userlogin.models.Conversation;
import com.example.userlogin.models.User;
import com.example.userlogin.repositories.ConversationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
/**
 * Esta clase es un adaptador de la interfaz ConversationPort.
 * Llama a ConversationRepository.
 */
@Component
public class ConversationJpaAdapter implements ConversationPort{

    private final ConversationRepository conversationRepository;

    public ConversationJpaAdapter(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Optional<Conversation> findById(Long conversationId) {
        return conversationRepository.findById(conversationId);
    }

    @Override
    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Optional<Conversation> findPrivateConversation(List<User> participant) {
        return conversationRepository.findPrivateConversation(participant);
    }

    @Override
    public Optional<Conversation> findByConversationName(String conversationName) {
        return conversationRepository.findByConversationName(conversationName);
    }
}
