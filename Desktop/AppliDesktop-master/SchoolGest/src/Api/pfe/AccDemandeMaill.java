/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api.pfe;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TOSHIBA
 */
public class AccDemandeMaill {
    public static void sendMail(String recipient) throws Exception{
        Properties properties = new Properties();
        //mail.smtp.auth    
        //mail.smtp.starttls.enable
        //mail.smtp.host=smtp.gmail.com
        //mail.smtp.port=587
        properties.put("mail.smtp.auth" , true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String email ="wazkasmi@gmail.com";
        String password="manoubacity";
        Session session = Session.getDefaultInstance(properties, new Authenticator(){
        protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(email, password);
        }
        });
        Message message = prepareMessage(session,email,recipient);
        Transport.send(message);
        System.out.println("votre demande d'encadrement est acceptee ");
    }

    private static Message prepareMessage(Session session, String email, String recipient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("demande encadrement acceptee");
            message.setText("vous avez  une demande d'encadrement qui a ete acceptee");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
}

