package com.optilife.service;

public interface EmailService {
    void enviarCorreo(String to, String subject, String body);

    void enviarEmailRecuperacion(String email, String token);
}

