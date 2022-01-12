package app.service.impl;

import app.domain.ReminderNotif;
import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import app.exception.NotFoundException;
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
        return reminderNotifRepository.findAll(pageable)
                .map(reminderNotifMapper::reminderNotifToReminderNotifDto);
    }

    @Override
    public ReminderNotifDto add(ReminderNotifCreateDto reminderNotifCreateDto) {
        ReminderNotif reminderNotif = reminderNotifMapper.reminderNotifCreateDtoToReminderNotif(reminderNotifCreateDto);
        reminderNotifRepository.save(reminderNotif);
        return reminderNotifMapper.reminderNotifToReminderNotifDto(reminderNotif);
    }

    @Override
    public ReminderNotifDto findById(Long id) {
        return reminderNotifRepository.findById((id))
                .map(reminderNotifMapper::reminderNotifToReminderNotifDto)
                .orElseThrow(() -> new NotFoundException(String.format("reminderNotifRepository with id: %d not found.", id)));
    }

    @Override
    public void deleteById(Long id) {
        reminderNotifRepository.deleteById((id));
    }
}
