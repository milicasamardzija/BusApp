package com.example.demo.service.email;

import com.example.demo.model.users.client.Passenger;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    public void sendEmailWithPdf(Passenger passenger){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            System.out.println("Pisem mejl.");
            Map<String, String> model = new HashMap<>();
            model.put("name", passenger.getName() + " " + passenger.getSurname());
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            ClassPathResource pdf = new ClassPathResource("static/ticket.pdf");
            Template template = configuration.getTemplate("email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(passenger.getEmail());
            helper.setFrom("sammilica99@gmail.com");
            helper.setSubject("Ticket");
            helper.setText(html, true);
            helper.addAttachment("ticket.pdf", pdf);
            mailSender.send(message);
            System.out.println("Poslao sam mejl.");
        } catch (MessagingException | IOException  e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailForVerification(String email, String code) {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Confirm your account");
            mimeMessageHelper.setFrom(new InternetAddress("sammilica99@gmail.com", "Bus app"));
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText("To confirm your account, please click here :" + "http://localhost:8081" + "/auth/confirm-account/" + code + ".\n");

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Email poslat!");
    }
}
