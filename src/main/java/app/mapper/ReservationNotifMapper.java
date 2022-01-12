package app.mapper;

import app.domain.Notification;
import app.domain.ReservationNotif;
import app.dto.ReservationNotifCreateDto;
import app.dto.ReservationNotifDto;
import app.exception.NotFoundException;
import app.repository.NotificationRepository;
import app.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class ReservationNotifMapper {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public ReservationNotifMapper(NotificationMapper notificationMapper, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.notificationMapper = notificationMapper;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public ReservationNotifDto reservationNotifToReservationNotifDto(ReservationNotif reservationNotif){

        ReservationNotifDto reservationNotifDto = new ReservationNotifDto();
        reservationNotifDto.setId(reservationNotif.getId());
        reservationNotifDto.setNotificationDto(notificationMapper.notificationToNotificationDto(reservationNotif.getNotification()));
        return reservationNotifDto;
    }

    public ReservationNotif reservationNotifCreateDtoToReservationNotif(ReservationNotifCreateDto reservationNotifCreateDto){
        Notification notification = new Notification();
        notification.setClientEmail(reservationNotifCreateDto.getClientEmail());
        notification.setText(reservationNotifCreateDto.getText());
        notification.setNotificationType(notificationTypeRepository.findByName(reservationNotifCreateDto.getType())
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", reservationNotifCreateDto.getType()))));
        notification.setCreationDate(reservationNotifCreateDto.getCreationDate());
        notificationRepository.save(notification);

        ReservationNotif reservationNotif = new ReservationNotif();
        reservationNotif.setNotification(notification);

        return reservationNotif;
    }
}
