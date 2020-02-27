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
public class JavaMailUtil {
    public static void sendMail(String recipient,String e) throws Exception{
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
        String info=e;

        Message message = prepareMessage(session,email,recipient,info);
        Transport.send(message);
        System.out.println("votre demande est envoyer merci d'attendre une reponse ");
    }

    private static Message prepareMessage(Session session, String email, String recipient,String info) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("demande encadrement");
            message.setText("vous avez recu une demande d'encadrement"+info);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
    
}
