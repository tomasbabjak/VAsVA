package executive;

import javax.activation.DataHandler;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Bean use to sending mail to customer
 * Mail contains attachment, respectively ticket pdf with qrcode
 */
@Stateless
public class MailSender {

    /**
     *
     * @param addresses email adress of recipient
     * @param topic topic of email
     * @param textMessage main text of message
     * @param pdf byte array that represent ticket in pdf format
     * @param image byte array image/qrcode
     */
    public void send(String addresses, String topic, String textMessage, byte[] pdf, byte[] image) {
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
            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(textMessage);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);

            //
            bodyPart = new MimeBodyPart();
            ByteArrayDataSource bds = new ByteArrayDataSource(pdf,"application/pdf");
            bodyPart.setDataHandler(new DataHandler(bds));
            bodyPart.setFileName("ticket.pdf");
            multipart.addBodyPart(bodyPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Sent...");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Cannot sent mail");
           // Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }

}
