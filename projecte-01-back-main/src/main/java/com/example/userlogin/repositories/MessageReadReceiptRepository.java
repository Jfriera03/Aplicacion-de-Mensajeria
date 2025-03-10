package com.example.userlogin.repositories;

import com.example.userlogin.models.MessageReadReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Esta interfaz es un repositorio de la entidad MessageReadReceipt.
 * Permite guardar instancias de MessageReadReceipt en la base de datos.
 * Permite buscar instancias de MessageReadReceipt en la base de datos.
 * Al extender de JpaRepository, hereda métodos como save, findById.
 */
public interface MessageReadReceiptRepository extends JpaRepository<MessageReadReceipt, Long> {
}
