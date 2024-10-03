package com.optilife.service.impl;

import com.optilife.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCorreo(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void enviarEmailRecuperacion(String email, String token) {
        // Código para enviar el correo electrónico con el token
        String subject = "Recuperación de contraseña";
        String body = "Utiliza este enlace para recuperar tu contraseña: " + "http://localhost:8080/reset?token=" + token;
        enviarCorreo(email, subject, body);
    }
}

