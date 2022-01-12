package app.mapper;

import app.domain.Notification;
import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationMapper() {
    }

    public NotificationDto notificationToNotificationDto (Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setClientEmail(notification.getClientEmail());
        notificationDto.setText(notification.getText());
        notificationDto.setType(notification.getType());
        notificationDto.setCreationDate(notification.getCreationDate());

        return notificationDto;
    }

    public Notification notificationCreateDtoToNotification (NotificationCreateDto notificationCreateDto){
        Notification notification = new Notification();
        notification.setClientEmail(notificationCreateDto.getClientEmail());
        notification.setText(notificationCreateDto.getText());
        notification.setType(notificationCreateDto.getType());
        notification.setCreationDate(notification.getCreationDate());

        return notification;
    }
}
