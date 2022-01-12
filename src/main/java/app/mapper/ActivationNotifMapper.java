package app.mapper;

import app.domain.ActivationNotif;
import app.domain.Notification;
import app.domain.NotificationType;
import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import app.exception.NotFoundException;
import app.repository.NotificationRepository;
import app.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivationNotifMapper {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public ActivationNotifMapper(NotificationMapper notificationMapper, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

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
        notification.setNotificationType(notificationTypeRepository.findByName(activationNotifCreateDto.getType())
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", activationNotifCreateDto.getType()))));
        notification.setCreationDate(activationNotifCreateDto.getCreationDate());
        notificationRepository.save(notification);

        ActivationNotif activationNotif = new ActivationNotif();
        activationNotif.setActivationLink(activationNotifCreateDto.getActivationLink());
        activationNotif.setNotification(notification);


        return activationNotif;
    }
}
