package app.service.impl;

import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import app.service.ReminderNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ReminderNotifServiceImpl implements ReminderNotifService {
    @Override
    public Page<ReminderNotifDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ReminderNotifDto add(ReminderNotifCreateDto reminderNotifCreateDto) {
        return null;
    }

    @Override
    public ReminderNotifDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
