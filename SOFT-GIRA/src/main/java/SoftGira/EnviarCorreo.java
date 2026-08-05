package SoftGira;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication; // <-- IMPORTANTE
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {

    // Configura tu correo de Gmail emisor y los datos de destino
    private static final String CORREO_EMISOR = "luis.lolis3025@gmail.com"; 
    private static final String CLAVE_APLICACION = "rize sujj pmkq msct"; // Contraseña de aplicación de 16 caracteres
    private static final String CORREO_DESTINO = "luis.lolis3025@gmail.com"; 

    public static boolean enviarReporte(String asunto, String descripcion, String prioridad) {
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CORREO_EMISOR, CLAVE_APLICACION);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(CORREO_EMISOR));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(CORREO_DESTINO));
            message.setSubject("[REPORTE SOFT-GIRA] [" + prioridad.toUpperCase() + "] " + asunto);

            String contenido = "===========================================\n"
                             + "    NUEVO REPORTE DE FALLA - SOFT-GIRA     \n"
                             + "===========================================\n\n"
                             + "Prioridad: " + prioridad + "\n"
                             + "Asunto: " + asunto + "\n\n"
                             + "Descripción del error:\n"
                             + "-------------------------------------------\n"
                             + descripcion + "\n"
                             + "-------------------------------------------\n";

            message.setText(contenido);

            Transport.send(message);
            return true;

        } catch (MessagingException e) {
            System.out.println("Error al enviar correo: " + e.getMessage());
            return false;
        }
    }
}