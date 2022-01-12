package app.mapper;

import app.domain.NotificationType;
import app.dto.NotificationTypeCreateDto;
import app.dto.NotificationTypeDto;
import org.springframework.stereotype.Component;

/**
 * Created on 12.01.2022. by Andrija inside package app.mapper.
 */
@Component
public class NotificationTypeMapper {

    public NotificationTypeDto notificationTypeToNotificationTypeDto(NotificationType notificationType){
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();
        notificationTypeDto.setId(notificationType.getId());
        notificationTypeDto.setName(notificationType.getName());
        return  notificationTypeDto;
    }
    public NotificationType notificationTypeCreateDtoToNotificationType(NotificationTypeCreateDto notificationTypeCreateDto){
        NotificationType notificationType = new NotificationType();
        notificationType.setName(notificationTypeCreateDto.getName());
        return notificationType;
    }
}
