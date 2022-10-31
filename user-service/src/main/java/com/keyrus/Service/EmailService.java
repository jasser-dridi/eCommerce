package com.keyrus.Service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EmailService {

    @Inject
    ReactiveMailer reactiveMailer;

    public void sendMail(String email, String message,String titlle)
    {
        reactiveMailer.send(Mail.withText(email,titlle,message));

    }
}
