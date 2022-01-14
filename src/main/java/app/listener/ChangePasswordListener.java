package app.listener;

import app.domain.Notification;
import app.domain.ResetNotif;
import app.dto.notificationDtos.UserPasswordDto;
import app.exception.NotFoundException;
import app.repository.NotificationRepository;
import app.repository.NotificationTypeRepository;
import app.repository.ResetNotifRepository;
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
public class ChangePasswordListener {
    private ObjectMapper objectMapper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;
    private ResetNotifRepository resetNotifRepository;
    private NotificationTypeRepository notificationTypeRepository;


    public ChangePasswordListener(ObjectMapper objectMapper, EmailService emailService, NotificationRepository notificationRepository,
                                  ResetNotifRepository resetNotifRepository, NotificationTypeRepository notificationTypeRepository) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.resetNotifRepository = resetNotifRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @JmsListener(destination = "${destination.resetPass}", concurrency = "5-10")
    public void resetPasswordNotif(Message message) throws JMSException, NotFoundException, JsonProcessingException {

        String json = ((TextMessage)message).getText();
        UserPasswordDto userPasswordDto = objectMapper.readValue(json, UserPasswordDto.class);


        String body = "Hello " + userPasswordDto.getName() + " " + userPasswordDto.getLastname() + " to save your new password please click on the given link:" +
                "http://localhost:8080/api/user/"+ userPasswordDto.getId() + "/saveNewPassword";


        Notification notification = new Notification();
        notification.setClientEmail(userPasswordDto.getEmail());
        notification.setText(body);
        notification.setNotificationType(notificationTypeRepository.findByName("reset")
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", "reset"))));
        notification.setCreationDate(LocalDate.now());
        notificationRepository.save(notification);

        ResetNotif resetNotif = new ResetNotif();
        resetNotif.setResetLink("http://localhost:8080/api/user/"+ userPasswordDto.getId() + "/saveNewPassword");
        resetNotif.setNotification(notification);
        resetNotifRepository.save(resetNotif);

        emailService.sendSimpleMessage(userPasswordDto.getEmail(),"Password reset save prompt", body);
    }
}
