package app.listener;



import app.dto.notificationDtos.VerifyMailDto;
import app.exception.NotFoundException;
import app.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class VerifyMailListener {
    private ObjectMapper objectMapper;
    private EmailService emailService;

    public VerifyMailListener(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.verifyMail}", concurrency = "5-10")
    public void sendVerifyMail(Message message)  throws JMSException, NotFoundException, JsonProcessingException{

        String json = ((TextMessage)message).getText();
        VerifyMailDto verifyMailDto =  objectMapper.readValue(json,VerifyMailDto.class);

        //todo kreiraj novu notifikasiju i postavi je na bazu

        System.out.println("Message procitan");
        System.out.println(verifyMailDto.getName());
        System.out.println(verifyMailDto.getLastname());
        System.out.println(verifyMailDto.getEmail());

        String text = "Hello" + verifyMailDto.getName() + " " + verifyMailDto.getLastname() + " to complete your registration please click on the given link: ";

        emailService.sendSimpleMessage("","test mail", "teraj se u kurac");


    }
}
