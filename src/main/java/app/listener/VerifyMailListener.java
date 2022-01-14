package app.listener;



import app.domain.ActivationNotif;
import app.domain.Notification;
import app.dto.notificationDtos.VerifyMailDto;
import app.exception.NotFoundException;
import app.repository.ActivationNotifRepository;
import app.repository.NotificationRepository;
import app.repository.NotificationTypeRepository;
import app.service.impl.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import java.time.LocalDate;

@Component
public class VerifyMailListener {
    private ObjectMapper objectMapper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;
    private ActivationNotifRepository activationNotifRepository;
    private NotificationTypeRepository notificationTypeRepository;


    public VerifyMailListener(ObjectMapper objectMapper, EmailService emailService, NotificationRepository notificationRepository,
                              ActivationNotifRepository activationNotifRepository, NotificationTypeRepository notificationTypeRepository) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.activationNotifRepository = activationNotifRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }


    @JmsListener(destination = "${destination.verifyMail}", concurrency = "5-10")
    public void sendVerifyMail(Message message)  throws JMSException, NotFoundException, JsonProcessingException{

        String json = ((TextMessage)message).getText();
        VerifyMailDto verifyMailDto =  objectMapper.readValue(json,VerifyMailDto.class);

        String body = "Hello " + verifyMailDto.getName() + " " + verifyMailDto.getLastname() + " to complete your registration please click on the given link:" +
                "http://localhost:8080/api/user/verifyMail/" + verifyMailDto.getEmail();

        Notification notification = new Notification();
        notification.setClientEmail(verifyMailDto.getEmail());
        notification.setText(body);
        notification.setNotificationType(notificationTypeRepository.findByName("activation")
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", "activation"))));
        notification.setCreationDate(LocalDate.now());
        notificationRepository.save(notification);

        ActivationNotif activationNotif = new ActivationNotif();
        activationNotif.setActivationLink( "http://localhost:8080/api/user/verifyMail/" + verifyMailDto.getEmail());
        activationNotif.setNotification(notification);
        activationNotifRepository.save(activationNotif);

        emailService.sendSimpleMessage(verifyMailDto.getEmail(), "Complete your registration", body);
    }
}
