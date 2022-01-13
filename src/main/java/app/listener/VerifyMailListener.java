package app.listener;



import app.domain.ActivationNotif;
import app.domain.Notification;
import app.domain.NotificationType;
import app.dto.notificationDtos.VerifyMailDto;
import app.exception.NotFoundException;
import app.repository.ActivationNotifRepository;
import app.repository.NotificationRepository;
import app.repository.NotificationTypeRepository;
import app.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.sql.Date;
import java.sql.Time;
import java.time.Clock;
import java.time.LocalDate;

@Component
public class VerifyMailListener {
    private ObjectMapper objectMapper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;
    private ActivationNotifRepository activationNotifRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public VerifyMailListener(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.verifyMail}", concurrency = "5-10")
    public void sendVerifyMail(Message message)  throws JMSException, NotFoundException, JsonProcessingException{

        String json = ((TextMessage)message).getText();
        VerifyMailDto verifyMailDto =  objectMapper.readValue(json,VerifyMailDto.class);

        //todo kreiraj novu notifikasiju i postavi je na bazu

        Notification notification = new Notification();
        notification.setClientEmail(verifyMailDto.getEmail());
        notification.setText("text main notif");
        notification.setNotificationType(notificationTypeRepository.findByName("activation")
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", "activation"))));
        //notification.setCreationDate(Date.from());
        notificationRepository.save(notification);

        ActivationNotif activationNotif = new ActivationNotif();
        //activationNotif.setActivationLink(activationNotifCreateDto.getActivationLink());
        activationNotif.setNotification(notification);



        String body = "Hello " + verifyMailDto.getName() + " " + verifyMailDto.getLastname() + " to complete your registration please click on the given link:" +
                "http://localhost:8080/api/user/verifyMail/" + verifyMailDto.getEmail();

        emailService.sendSimpleMessage(verifyMailDto.getEmail(), "Complete your registration", body);


    }
}
