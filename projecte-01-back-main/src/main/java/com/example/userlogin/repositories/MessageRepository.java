package com.example.userlogin.repositories;

import com.example.userlogin.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/*
 * Esta interfaz es un repositorio de la entidad Message.
 * Permite guardar instancias de Message en la base de datos.
 * Permite buscar instancias de Message en la base de datos.
 * Al extender de JpaRepository, hereda métodos como save, findById, delete, etc.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Buscar un mensaje por el id de la conversación y por fecha de envío
    List<Message> findByConversationIdAndSentDate(Long conversationId, LocalDate sentdate);
}