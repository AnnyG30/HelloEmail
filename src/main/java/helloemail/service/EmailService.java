package helloemail.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {this.mailSender = mailSender;}

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

     public void sendMailWithMultipleRecipients(String [] to, String subject, String body) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHTMLMessage(String[] to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

       message.setRecipients(Message.RecipientType.TO, Arrays.toString(to));
       message.setSubject(subject);

       message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }
}
