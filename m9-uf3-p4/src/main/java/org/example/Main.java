package org.example;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jinshuo.m6@gmail.com", "745963217jins");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("jinshuo.m6@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("jinshuo.xia8@gmail.com"));
            message.setSubject("Asunto del correo");
            message.setText("Contenido del mensaje");

            Transport.send(message);
            System.out.println("Correo enviado exitosamente.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
