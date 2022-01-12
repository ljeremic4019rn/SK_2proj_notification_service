package app.service.impl;

import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import app.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class NotificationServiceImpl implements NotificationService {
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
