package app.service;

import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReminderNotifService {
    Page<ReminderNotifDto> findAll(Pageable pageable);

    ReminderNotifDto add(ReminderNotifCreateDto reminderNotifCreateDto);

    ReminderNotifDto findById(Long id);

    void deleteById(Long id);
}
