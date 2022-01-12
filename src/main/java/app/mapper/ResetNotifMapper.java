package app.mapper;

import app.domain.Notification;
import app.domain.ResetNotif;
import app.dto.NotificationDto;
import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import app.exception.NotFoundException;
import app.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
public class ResetNotifMapper {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;

    public ResetNotifMapper(NotificationMapper notificationMapper, NotificationRepository notificationRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
    }

    public ResetNotifDto resetNotifToResetNotifDto(ResetNotif resetNotif){
        ResetNotifDto resetNotifDto = new ResetNotifDto();
        resetNotifDto.setId(resetNotif.getId());
        resetNotifDto.setNewEmail(resetNotif.getNewEmail());
        resetNotifDto.setNewPassword(resetNotif.getNewPassword());
        resetNotifDto.setNotificationDto(notificationMapper.notificationToNotificationDto(resetNotif.getNotification()));
        return resetNotifDto;
    }

    public ResetNotif resetNotifCreateDtoToResetNotif(ResetNotifCreateDto resetNotifCreateDto){
        Notification notification = new Notification();
        notification.setClientEmail(resetNotifCreateDto.getClientEmail());
        notification.setText(resetNotifCreateDto.getText());
        notification.setType(resetNotifCreateDto.getType());
        notification.setCreationDate(resetNotifCreateDto.getCreationDate());
        notificationRepository.save(notification);

        ResetNotif resetNotif = new ResetNotif();
        resetNotif.setNewEmail(resetNotifCreateDto.getNewEmail());
        resetNotif.setNewPassword(resetNotifCreateDto.getNewPassword());
        resetNotif.setNotification(notification);

        return resetNotif;
    }
}
