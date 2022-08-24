package com.example.demo;

import com.example.demo.service.email.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//@EventListener(ApplicationReadyEvent.class)
	public void sendMail() throws InterruptedException {
		System.out.println("********************************************************");
		this.emailSenderService.sendEmailWithPdf(5);
		System.out.println("********************************************************");
	}

}
