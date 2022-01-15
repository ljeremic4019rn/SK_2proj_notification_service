package app.mapper;

import app.domain.ActivationNotif;
import app.domain.ResetNotif;
import app.dto.CustomNotificationDto;
import org.springframework.stereotype.Component;

/**
 * Created on 15.01.2022. by Andrija inside package app.mapper.
 */
@Component
public class CustomNotificationMapper {

    public CustomNotificationDto resetNotifToCustomNotificationDto(ResetNotif resetNotif){
        CustomNotificationDto customNotificationDto = new CustomNotificationDto();
        customNotificationDto.setEmail(resetNotif.getNotification().getClientEmail());
        customNotificationDto.setText(resetNotif.getNotification().getText());
        customNotificationDto.setType(resetNotif.getNotification().getNotificationType().getName());
        customNotificationDto.setCreationDate(resetNotif.getNotification().getCreationDate());
        return customNotificationDto;
    }

    public CustomNotificationDto activationNotifToCustomNotificationDto(ActivationNotif activationNotif){
        CustomNotificationDto customNotificationDto = new CustomNotificationDto();
        customNotificationDto.setEmail(activationNotif.getNotification().getClientEmail());
        customNotificationDto.setText(activationNotif.getNotification().getText());
        customNotificationDto.setType(activationNotif.getNotification().getNotificationType().getName());
        customNotificationDto.setCreationDate(activationNotif.getNotification().getCreationDate());
        return customNotificationDto;
    }
}
