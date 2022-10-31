package com.keyrus.consumer;

import com.keyrus.Service.EmailService;
import com.keyrus.Service.UserService;
import com.keyrus.modeldto.EmailDto;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@ApplicationScoped
public class MailConsumer {

    @Inject
    EmailService emailService;
    @Incoming("mail-in")
    public void sendEmail(EmailDto emailDto)
    {
        System.out.println("inside sendEmail");
        emailService.sendMail(emailDto.email, emailDto.message, "NOTIFICATION");
    }
}
