package com.example.userlogin.ports;

import com.example.userlogin.models.Message;
import com.example.userlogin.repositories.MessageRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
    * Esta clase es un adaptador de la interfaz MessagePort.
    * Llama a MessageRepository.
 */

@Component
public class MessageJpaAdapter implements MessagePort {

    private final MessageRepository messageRepository;

    public MessageJpaAdapter(MessageRepository messageRepository) {

        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> findById(Long messageId) {

        return messageRepository.findById(messageId);
    }

    @Override
    public Message saveMessage(Message message) {

        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Message message) {

        messageRepository.delete(message);
    }

    @Override
    public List<Message> findByConversationIdAndSentDate(Long conversationId, LocalDate sentdate) {

        return messageRepository.findByConversationIdAndSentDate(conversationId, sentdate);
    }
}
