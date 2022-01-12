package app.mapper;

import app.domain.Notification;
import app.domain.ReminderNotif;
import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import app.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
public class ReminderNotifMapper {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;

    public ReminderNotifDto reminderNotifToReminderNotifDto (ReminderNotif reminderNotif){
        ReminderNotifDto reminderNotifDto = new ReminderNotifDto();
        reminderNotifDto.setId(reminderNotif.getId());
        reminderNotifDto.setTimeUntilReservation(reminderNotif.getTimeUntilReservation());
        reminderNotifDto.setNotificationDto(notificationMapper.notificationToNotificationDto(reminderNotif.getNotification()));

        return reminderNotifDto;
    }

    public ReminderNotif reminderNotifCreateDtoToReminderNotif(ReminderNotifCreateDto reminderNotifCreateDto){
        Notification notification = new Notification();
        notification.setClientEmail(reminderNotifCreateDto.getClientEmail());
        notification.setText(reminderNotifCreateDto.getText());
        notification.setType(reminderNotifCreateDto.getType());
        notification.setCreationDate(reminderNotifCreateDto.getCreationDate());
        notificationRepository.save(notification);

        ReminderNotif reminderNotif = new ReminderNotif();
        reminderNotif.setTimeUntilReservation(reminderNotifCreateDto.getTimeUntilReservation());
        reminderNotif.setNotification(notification);

        return reminderNotif;
    }
}
