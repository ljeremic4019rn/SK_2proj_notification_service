package app.service.impl;

import app.domain.ActivationNotif;
import app.domain.Notification;
import app.dto.ActivationNotifDto;
import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import app.exception.NotFoundException;
import app.mapper.NotificationMapper;
import app.repository.NotificationRepository;
import app.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::notificationToNotificationDto);
    }

    @Override
    public NotificationDto add(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationCreateDtoToNotification(notificationCreateDto);
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public NotificationDto findById(Long id) {
        return notificationRepository.findById((id))
                .map(notificationMapper::notificationToNotificationDto)
                .orElseThrow(() -> new NotFoundException(String.format("notificationRepository with id: %d not found.", id)));
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById((id));
    }



    @Override
    public Page<NotificationDto> findByType(String type, Pageable pageable) {
        List<Notification> notifications = notificationRepository.findAll();
        ArrayList<NotificationDto> notificationDtos = new ArrayList<>();

        for(Notification an: notifications){
            if(an.getNotificationType().getName().equals(type)){
                notificationDtos.add(notificationMapper.notificationToNotificationDto(an));
            }
        }

        Page<NotificationDto> notificationDtoPage = new PageImpl<>(notificationDtos);
        return notificationDtoPage;
    }

    @Override
    public Page<NotificationDto> findByEmail(String email, Pageable pageable) {
        List<Notification> activationNotifs = notificationRepository.findAll();
        ArrayList<NotificationDto> SelectedNotifDtos = new ArrayList<>();

        for(Notification an : activationNotifs) {
            if(an.getClientEmail().equals(email)){
                SelectedNotifDtos.add(notificationMapper.notificationToNotificationDto(an));
            }
        }

        Page<NotificationDto> notificationDtoPage = new PageImpl<>(SelectedNotifDtos);
        return notificationDtoPage;
    }

}
