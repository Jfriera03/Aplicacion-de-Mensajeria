package com.example.userlogin.ports;

import com.example.userlogin.models.MessageReadReceipt;
import com.example.userlogin.repositories.MessageReadReceiptRepository;
import org.springframework.stereotype.Component;

/**
    * Esta clase es un adaptador de la interfaz MessageReadReceiptPort.
    * Llama a MessageReadReceiptRepository.
 */
@Component
public class MessageReadReceiptJpaAdapter implements MessageReadReceiptPort {

    private final MessageReadReceiptRepository messageReadReceiptRepository;

    public MessageReadReceiptJpaAdapter(MessageReadReceiptRepository messageReadReceiptRepository) {
        this.messageReadReceiptRepository = messageReadReceiptRepository;
    }

    @Override
    public MessageReadReceipt save(MessageReadReceipt messageReadReceipt) {
        return messageReadReceiptRepository.save(messageReadReceipt);
    }
}
