// Test para la clase MessageReadReceipt
package com.example.userlogin.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageReadReceiptTest {

    @Test
    void testMessageReadReceiptConstructorAndGetters() {
        // Crear instancias de Message y User
        Message message = new Message();
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        // Crear y establecer propiedades de MessageReadReceipt
        MessageReadReceipt receipt = new MessageReadReceipt();
        receipt.setId(1L);
        receipt.setMessage(message);
        receipt.setUser(user);
        receipt.setReadTimestamp(now);

        // Verificar las propiedades
        assertEquals(1L, receipt.getId());
        assertEquals(message, receipt.getMessage());
        assertEquals(user, receipt.getUser());
        assertEquals(now, receipt.getReadTimestamp());
    }

    @Test
    void testMessageReadReceiptDefaults() {
        // Crear un nuevo MessageReadReceipt
        MessageReadReceipt receipt = new MessageReadReceipt();

        // Verificar que el timestamp de lectura no sea nulo
        assertNotNull(receipt.getReadTimestamp());
    }
}