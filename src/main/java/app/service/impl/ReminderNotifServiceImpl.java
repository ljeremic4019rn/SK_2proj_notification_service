package app.service.impl;

import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import app.mapper.ReminderNotifMapper;
import app.repository.ReminderNotifRepository;
import app.service.ReminderNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReminderNotifServiceImpl implements ReminderNotifService {

   private ReminderNotifRepository reminderNotifRepository;
   private ReminderNotifMapper reminderNotifMapper;

    public ReminderNotifServiceImpl(ReminderNotifRepository reminderNotifRepository, ReminderNotifMapper reminderNotifMapper) {
        this.reminderNotifRepository = reminderNotifRepository;
        this.reminderNotifMapper = reminderNotifMapper;
    }

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
