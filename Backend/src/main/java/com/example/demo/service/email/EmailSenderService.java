package com.example.demo.service.email;

import com.example.demo.model.users.User;
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
            ClassPathResource pdf = new ClassPathResource("static/karta.pdf");
            Template template = configuration.getTemplate("email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(passenger.getEmail());
            helper.setFrom("sammilica99@gmail.com");
            helper.setSubject("Karta");
            helper.setText(html, true);
            helper.addAttachment("karta.pdf", pdf);
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
            mimeMessageHelper.setSubject("Potvrda naloga");
            mimeMessageHelper.setFrom(new InternetAddress("sammilica99@gmail.com", "Bus app"));
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText("Da biste potvrdili registraciju kliknite na ovaj link :" + "http://localhost:8081" + "/auth/confirm-account/" + code + ".\n");

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Email poslat!");
    }

    public void sendEmailForDiscountRejection(String email) {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Odbijen zahtev za popust");
            mimeMessageHelper.setFrom(new InternetAddress("sammilica99@gmail.com", "Bus app"));
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText("Vas dokaz o ptrebnim uslovima za ostvarivanje popusta nije prosao validaciju naseg sluzbenika.Molimo slikajte potreban dokument(indeks, penzionersku legitimaciju i sl.) i posaljite ponovo zahtev.Hvala!");

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Email poslat!");
    }

    public void sendEmailWithPdfForDailyReport(User user) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            System.out.println("Pisem mejl.");
            Map<String, String> model = new HashMap<>();
            model.put("name", user.getName() + " " + user.getSurname());
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            ClassPathResource pdf = new ClassPathResource("static/dnevni_izvestaj.pdf");
            Template template = configuration.getTemplate("emailReport.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(user.getEmail());
            helper.setFrom("sammilica99@gmail.com");
            helper.setSubject("Izvestaj");
            helper.setText(html, true);
            helper.addAttachment("dnevni_izvestaj.pdf", pdf);
            mailSender.send(message);
            System.out.println("Poslao sam mejl.");
        } catch (MessagingException | IOException  e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public void sendMonthlyTicketRejection(Passenger passenger) {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Odbijen zahtev za mesecnu kartu");
            mimeMessageHelper.setFrom(new InternetAddress("sammilica99@gmail.com", "Bus app"));
            mimeMessageHelper.setTo(passenger.getEmail());
            mimeMessageHelper.setText("Vas zahtev za mesecnu kartu je odbijen.Za vise informacija javite se nasoj sluzbi.Hvala.");

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Email poslat!");
    }

    public void acceptDeleteRequest(User user) {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Odbijen zahtev za popust");
            mimeMessageHelper.setFrom(new InternetAddress("sammilica99@gmail.com", "Bus app"));
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setText("Vas zahtev za brisanje naloga je prihvacen.");

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Email poslat!");
    }

    public void rejectDeleteRequest(User user) {
        System.out.println("Slanje emaila...");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Odbijen zahtev za popust");
            mimeMessageHelper.setFrom(new InternetAddress("sammilica99@gmail.com", "Bus app"));
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setText("Vas zahtev za brisanje naloga je odbijen.Kontaktirajte naseg administratora za vise informacija.");

            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("Email poslat!");
    }
}
