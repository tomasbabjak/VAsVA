package executive;


import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Stateless
public class MailSender {

    public void send(String addresses, String topic, String textMessage) {
        Properties properties = new Properties();
        String fileName = System.getProperty("jboss.server.config.dir") + "\\my.properties";
        try{
            FileInputStream fis = new FileInputStream(fileName);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(properties == null){
            System.out.println("Propertis je null");
        }
        Properties props = System.getProperties();
        Session session = Session.getInstance(properties,new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.username"), properties.getProperty("mail.password"));
            }
        });

        try {
            Message message = new MimeMessage(session);
            InternetAddress from = new InternetAddress("vasva.cinema@gmail.com");
            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);
            Transport.send(message);
            System.out.println("Sent...");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Cannot sent mail");
           // Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }

}
