package com.example.userlogin.repositories;

import com.example.userlogin.models.Conversation;
import com.example.userlogin.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/*
    * Esta interfaz es un repositorio de la entidad Conversation.
    * Permite guardar instancias de Conversation en la base de datos.
    * Permite buscar instancias de Conversation en la base de datos.
    * Al extender de JpaRepository, hereda métodos como save o findById.
 */
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    // Buscar una conversación (grupal) por el nombre de la conversación
    Optional<Conversation> findByConversationName(String conversationName);
    // Buscar una conversación privada por los usuarios que participan en ella
    @Query("SELECT c FROM Conversation c JOIN c.users u WHERE u IN :users")
    Optional<Conversation> findPrivateConversation(@Param("users") List<User> users);
}