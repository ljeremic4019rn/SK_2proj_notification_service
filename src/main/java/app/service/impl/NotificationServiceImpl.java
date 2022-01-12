package app.service.impl;

import app.domain.Notification;
import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import app.exception.NotFoundException;
import app.mapper.NotificationMapper;
import app.repository.NotificationRepository;
import app.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
