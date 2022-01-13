package app.mapper;

import app.domain.Notification;
import app.domain.ResetNotif;
import app.dto.NotificationDto;
import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import app.exception.NotFoundException;
import app.repository.NotificationRepository;
import app.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class ResetNotifMapper {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public ResetNotifMapper(NotificationMapper notificationMapper, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public ResetNotifDto resetNotifToResetNotifDto(ResetNotif resetNotif){
        ResetNotifDto resetNotifDto = new ResetNotifDto();
        resetNotifDto.setId(resetNotif.getId());
        resetNotifDto.setResetLink(resetNotif.getResetLink());
        resetNotifDto.setNotificationDto(notificationMapper.notificationToNotificationDto(resetNotif.getNotification()));
        return resetNotifDto;
    }

    public ResetNotif resetNotifCreateDtoToResetNotif(ResetNotifCreateDto resetNotifCreateDto){
        Notification notification = new Notification();
        notification.setClientEmail(resetNotifCreateDto.getClientEmail());
        notification.setText(resetNotifCreateDto.getText());
        notification.setNotificationType(notificationTypeRepository.findByName(resetNotifCreateDto.getType())
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", resetNotifCreateDto.getType()))));
        notification.setCreationDate(resetNotifCreateDto.getCreationDate());
        notificationRepository.save(notification);

        ResetNotif resetNotif = new ResetNotif();
        resetNotif.setResetLink(resetNotifCreateDto.getResetLink());
        resetNotif.setNotification(notification);

        return resetNotif;
    }
}
