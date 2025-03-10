// Test para la clase Message
package com.example.userlogin.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testMessageConstructorAndGetters() {
        // Crear instancias de Conversation y User
        Conversation conversation = new Conversation();
        User sender = new User();
        String content = "Hello, World!";
        LocalDate sentDate = LocalDate.now();
        LocalTime sentTime = LocalTime.now();

        // Crear y establecer propiedades de Message
        Message message = new Message();
        message.setId(1L);
        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(content);
        message.setSentDate(sentDate);
        message.setSentTime(sentTime);

        // Verificar las propiedades
        assertEquals(1L, message.getId());
        assertEquals(conversation, message.getConversation());
        assertEquals(sender, message.getSender());
        assertEquals(content, message.getContent());
        assertEquals(sentDate, message.getSentDate());
        assertEquals(sentTime, message.getSentTime());
    }

    @Test
    void testMessageDefaults() {
        // Crear un nuevo Message
        Message message = new Message();

        // Verificar que la fecha y hora de envío no sean nulas
        assertNotNull(message.getSentDate());
        assertNotNull(message.getSentTime());
    }
}