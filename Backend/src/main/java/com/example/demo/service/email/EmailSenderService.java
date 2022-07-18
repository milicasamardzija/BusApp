package com.example.demo.service.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    public void sendEmail(){
        SimpleMailMessage m = new SimpleMailMessage();
        m.setFrom("sammilica99@gmail.com");
        m.setTo("sammilica99@gmail.com");
        m.setText("AAAAAAAAAAA");
        m.setSubject("aaa");
        mailSender.send(m);
    }

    public void sendEmailWithPdf(){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            Map<String, String> model = new HashMap<>();
            model.put("name", "sammilica99@gmail.com");
            model.put("value", "Welcome to ASB Notebook!!");
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            ClassPathResource pdf = new ClassPathResource("static/a.pdf");
            ClassPathResource image = new ClassPathResource("static/b.png");
            Template template = configuration.getTemplate("email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo("sammilica99@gmail.com");
            helper.setFrom("sammilica99@gmail.com");
            helper.setSubject("BBB");
            helper.setText(html, true);
            helper.addInline("asbnotebook", image);
            helper.addAttachment("a.pdf", pdf);
            mailSender.send(message);
        } catch (MessagingException | IOException  e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
