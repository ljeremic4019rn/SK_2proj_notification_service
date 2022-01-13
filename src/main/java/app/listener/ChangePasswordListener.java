package app.listener;

import app.dto.notificationDtos.UserPasswordDto;
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
public class ChangePasswordListener {
    private ObjectMapper objectMapper;
    private EmailService emailService;

    public ChangePasswordListener(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.resetPass}", concurrency = "5-10")
    public void resetPasswordNotif(Message message) throws JMSException, NotFoundException, JsonProcessingException {

        String json = ((TextMessage)message).getText();
        UserPasswordDto userPasswordDto = objectMapper.readValue(json, UserPasswordDto.class);

        //todo kreiraj novu notifikasiju i postavi je na bazu




        System.out.println("MESSAGE PROCITAN");
        System.out.println(userPasswordDto.getEmail());
        System.out.println(userPasswordDto.getPassword());

        System.out.println("Sending email");

        emailService.sendSimpleMessage("bogdanovicjovan04@gmail.com","test mail", "teraj se u kurac");



    }

}
