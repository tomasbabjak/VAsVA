package executive;

//import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Stateless
public class MailSender {

//    @Resource(name = "java:jboss/mail/gmail")
//    private Session session;
    final String username = "vasva.cinema@gmail.com";
    final String password = "polikuj1";

    public void send(String addresses, String topic, String textMessage) {

        Properties prop = null;
        //        prop.load(new FileInputStream("C:\\Users\\minar\\Desktop\\VAVA_intellij\\09\\etc\\conf.properties"));
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//        props.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        Session session = Session.getInstance(props,new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            InternetAddress from = new InternetAddress("vasva.cinema@gmail.com");
            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);
            System.out.println("Prepared to sent...");
            Transport.send(message);
            System.out.println("Sent...");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Cannot sent mail");
           // Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }

    private void setProp(Properties props) {
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
    }


}
