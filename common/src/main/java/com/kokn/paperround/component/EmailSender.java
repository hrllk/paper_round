package com.kokn.paperround.component;

import com.kokn.paperround.model.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSender {

    private final static String TEMPLATE_WELCOME = "welcome";


    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;


    public void sendEmail(Mail mail) throws MessagingException, IOException{

        MimeMessage message = emailSender.createMimeMessage() ;
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.addAttachment(mail.getFile().getName(), mail.getFile());
        Context context = new Context();
//        context.setVariables();

        String html = templateEngine.process("helloworld", context);

        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(html, true);
    }


    /***
     * subscriptionMail
     */
    public void sendSubscriptionMail(String signupLink, String email) {

        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            Context context = new Context();
            context.setVariable("link", signupLink);

            helper.setFrom(sender);
            helper.setTo(email);
            helper.setSubject("가입메일");
            helper.setText(templateEngine.process(TEMPLATE_WELCOME, context), true);
        } catch (MessagingException e) {
            log.debug("ERROR: ", e);
        }
        emailSender.send(message);
    }



}
