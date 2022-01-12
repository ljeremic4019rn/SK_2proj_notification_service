package app.service;

import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    Page<NotificationDto> findAll(Pageable pageable);

    NotificationDto add(NotificationCreateDto notificationCreateDto);

    NotificationDto findById(Long id);

    void deleteById(Long id);

}
