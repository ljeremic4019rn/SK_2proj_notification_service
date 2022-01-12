package app.mapper;

import app.domain.ActivationNotif;
import app.domain.Notification;
import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import app.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivationNotifMapper {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;



    public ActivationNotifDto activationNotifToActivationNotifDto (ActivationNotif activationNotif){
        ActivationNotifDto activationNotifDto = new ActivationNotifDto();
        activationNotifDto.setId(activationNotif.getId());
        activationNotifDto.setActivationLink(activationNotif.getActivationLink());
        activationNotifDto.setNotificationDto(notificationMapper.notificationToNotificationDto(activationNotif.getNotification()));

        return  activationNotifDto;
    }

    public ActivationNotif activationNotifCreateDtoToActivationNotif (ActivationNotifCreateDto activationNotifCreateDto){
        Notification notification = new Notification();
        notification.setClientEmail(activationNotifCreateDto.getClientEmail());
        notification.setText(activationNotifCreateDto.getText());
        notification.setType(activationNotifCreateDto.getType());
        notification.setCreationDate(activationNotifCreateDto.getCreationDate());
        notificationRepository.save(notification);

        ActivationNotif activationNotif = new ActivationNotif();
        activationNotif.setActivationLink(activationNotifCreateDto.getActivationLink());
        activationNotif.setNotification(notification);


        return activationNotif;
    }
}
