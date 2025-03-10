package com.example.userlogin.ports;

import com.example.userlogin.models.MessageReadReceipt;

public interface MessageReadReceiptPort {
    // Guarda un registro de lectura de mensaje
    MessageReadReceipt save(MessageReadReceipt messageReadReceipt);

}
