package helloemail.controller;

import helloemail.request.EmailRequest;
import helloemail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail/api")
public class HelloEmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendSimpleMail")
    public void sendSimpleMail(@RequestBody EmailRequest request) {
        emailService.sendEmail(
                request.getTo(),
                request.getSubject(),
                request.getBody()
        );}


    @PostMapping("/sendToMultipleRecipients")
    public void sendToMultipleRecipients(@RequestBody EmailRequest request) throws MessagingException {
        String recipients = request.getTo();
        String [] myRecipients = recipients.split(";");
        emailService.sendMailWithMultipleRecipients(myRecipients, request.getSubject(), request.getBody());
    }


    @PostMapping("/sendHTMLMessage")
    public void sendHTMLMessage(@RequestBody EmailRequest request) throws MessagingException {
        String recipients = request.getTo();
        String [] myRecipients = recipients.split(";");

        emailService.sendHTMLMessage(myRecipients,  request.getSubject(), request.getBody());
    }

    
}
