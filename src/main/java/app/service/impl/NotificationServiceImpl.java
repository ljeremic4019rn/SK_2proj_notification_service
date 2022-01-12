package app.service.impl;

import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
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
        return null;
    }

    @Override
    public NotificationDto add(NotificationCreateDto notificationCreateDto) {
        return null;
    }

    @Override
    public NotificationDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
