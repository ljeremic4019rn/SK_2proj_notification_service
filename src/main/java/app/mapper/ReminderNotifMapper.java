package app.mapper;

import app.domain.Notification;
import app.domain.ReminderNotif;
import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import app.exception.NotFoundException;
import app.repository.NotificationRepository;
import app.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReminderNotifMapper {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public ReminderNotifMapper(NotificationMapper notificationMapper, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

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
        notification.setNotificationType(notificationTypeRepository.findByName(reminderNotifCreateDto.getType())
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", reminderNotifCreateDto.getType()))));
        notification.setCreationDate(LocalDate.now());
        notificationRepository.save(notification);

        ReminderNotif reminderNotif = new ReminderNotif();
        reminderNotif.setTimeUntilReservation(reminderNotifCreateDto.getTimeUntilReservation());
        reminderNotif.setNotification(notification);

        return reminderNotif;
    }
}
