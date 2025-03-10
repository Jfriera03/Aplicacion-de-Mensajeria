// Test para la clase Conversation
package com.example.userlogin.models;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversationTest {

    @Test
    void testConversationConstructorAndGetters() {
        // Crear instancias de nombre de conversación, usuarios y mensajes
        String conversationName = "Test Conversation";
        List<User> users = new ArrayList<>();
        List<Message> messages = new ArrayList<>();

        // Crear y establecer propiedades de Conversation
        Conversation conversation = new Conversation();
        conversation.setId(1L);
        conversation.setConversationName(conversationName);
        conversation.setUsers(users);
        conversation.setMessages(messages);

        // Verificar las propiedades
        assertEquals(1L, conversation.getId());
        assertEquals(conversationName, conversation.getConversationName());
        assertEquals(users, conversation.getUsers());
        assertEquals(messages, conversation.getMessages());
    }

    @Test
    void testConversationDefaults() {
        // Crear una nueva Conversation
        Conversation conversation = new Conversation();

        // Verificar los valores por defecto
        assertNull(conversation.getConversationName());
        assertNotNull(conversation.getUsers());
        assertNotNull(conversation.getMessages());
    }
}