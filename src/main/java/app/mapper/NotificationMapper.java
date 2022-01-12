package app.mapper;

import app.domain.Notification;
import app.domain.NotificationType;
import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import app.exception.NotFoundException;
import app.repository.NotificationTypeRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    private NotificationTypeMapper notificationTypeMapper;
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationMapper(NotificationTypeMapper notificationTypeMapper, NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeMapper = notificationTypeMapper;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public NotificationDto notificationToNotificationDto (Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setClientEmail(notification.getClientEmail());
        notificationDto.setText(notification.getText());
        notificationDto.setNotificationTypeDto(notificationTypeMapper.notificationTypeToNotificationTypeDto(notification.getNotificationType()));
        notificationDto.setCreationDate(notification.getCreationDate());

        return notificationDto;
    }

    public Notification notificationCreateDtoToNotification (NotificationCreateDto notificationCreateDto){

        Notification notification = new Notification();
        notification.setClientEmail(notificationCreateDto.getClientEmail());
        notification.setText(notificationCreateDto.getText());
        notification.setNotificationType(notificationTypeRepository.findByName(notificationCreateDto.getType())
                .orElseThrow(() -> new NotFoundException(String
                        .format("NotificationType with name: %s does not exists.", notificationCreateDto.getType()))));
        notification.setCreationDate(notification.getCreationDate());

        return notification;
    }
}
