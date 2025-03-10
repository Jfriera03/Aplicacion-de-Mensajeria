package com.example.userlogin.services;

import com.example.userlogin.models.Conversation;
import com.example.userlogin.models.Message;
import com.example.userlogin.models.MessageReadReceipt;
import com.example.userlogin.models.User;
import com.example.userlogin.ports.ConversationPort;
import com.example.userlogin.ports.MessagePort;
import com.example.userlogin.ports.MessageReadReceiptPort;
import com.example.userlogin.ports.UserPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/*
    * En MessagingServiceImpl se utilizan puertos en lugar de repositorios directamente para seguir el principio
    * de la arquitectura hexagonal. Proporcionando una capa de abstracción entre la lógica de negocio y la
    * capa de persistencia.
 */
@Service
public class MessagingServiceImpl implements MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(MessagingServiceImpl.class);
    private final ConversationPort conversationPort;
    private final MessagePort messagePort;
    private final UserPort userPort;
    private final MessageReadReceiptPort messageReadReceiptPort;

    public MessagingServiceImpl(ConversationPort conversationPort, MessagePort messagePort, UserPort userPort, MessageReadReceiptPort messageReadReceiptPort) {
        this.conversationPort = conversationPort;
        this.messagePort = messagePort;
        this.userPort = userPort;
        this.messageReadReceiptPort = messageReadReceiptPort;
    }

    // ENVIAR UN MENSAJE A UNA CONVERSACIÓN
    @Override
    public Message sendMessage(Long conversationId, String authenticatedUsername, String content) {
        // Validar que el contenido del mensaje no esté vacío
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }

        // Validar la existencia de la conversación
        Conversation conversation = conversationPort.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        // Validar la existencia del usuario autenticado
        User sender = userPort.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        // Verificar que el usuario pertenece a la conversación
        if (!conversation.getUsers().contains(sender)) {
            throw new RuntimeException("User is not a participant in this conversation");
        }

        // Crear y guardar el mensaje
        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setSentDate(LocalDate.now());
        message.setSentTime(LocalTime.now());
        message.setContent(content);
        return messagePort.saveMessage(message);
    }

    // ELIMINAR UN MENSAJE DE UNA CONVERSACIÓN
    @Override
    public void deleteMessageById(Long messageId, String username) {
        // Buscar el mensaje por ID
        Message message = messagePort.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with ID: " + messageId));

        // Validar que el usuario autenticado es el remitente del mensaje
        if (!message.getSender().getUsername().equals(username)) {
            throw new RuntimeException("User is not authorized to delete this message");
        }

        // Validar que el usuario pertenece a la conversación
        User user = userPort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!message.getConversation().getUsers().contains(user)) {
            throw new RuntimeException("User is not a participant in this conversation");
        }

        // Eliminar el mensaje
        messagePort.deleteMessage(message);
    }

    // OBTENER MENSAJES DE UNA CONVERSACIÓN
    @Override
    public List<Message> getMessagesByConversation(Long conversationId, LocalDate sentdate) {
        return messagePort.findByConversationIdAndSentDate(conversationId, sentdate);
    }

    // MARCAR UN MENSAJE COMO LEÍDO
    public void markMessageAsRead(Long messageId, String username) {
        logger.info("Marking message with ID {} as read by user {}", messageId, username);
        try {
            Message message = messagePort.findById(messageId)
                    .orElseThrow(() -> new RuntimeException("Message not found"));

            User user = userPort.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            if (!message.getConversation().getUsers().contains(user)) {
                throw new RuntimeException("User is not a participant in this conversation");
            }

            MessageReadReceipt receipt = new MessageReadReceipt();
            receipt.setMessage(message);
            receipt.setUser(user);
            messageReadReceiptPort.save(receipt);
        } catch (RuntimeException e) {
            logger.error("Error marking message as read: {}", e.getMessage());
            // Continue with the flow even if an exception occurs
        }
    }
}