package com.example.userlogin.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void testProfileConstructorAndGetters() {
        // Crear una instancia de Profile y establecer sus propiedades
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setPhoneNumber("1234567890");
        profile.setAddress("123 Main St");

        // Verificar las propiedades del Profile
        assertEquals(1L, profile.getId());
        assertEquals("John", profile.getFirstName());
        assertEquals("Doe", profile.getLastName());
        assertEquals("1234567890", profile.getPhoneNumber());
        assertEquals("123 Main St", profile.getAddress());
    }

    @Test
    void testProfileDefaults() {
        // Crear una nueva instancia de Profile
        Profile profile = new Profile();

        // Verificar los valores por defecto de las propiedades
        assertEquals("Default First Name", profile.getFirstName());
        assertEquals("Default Last Name", profile.getLastName());
    }
}