package app.service.impl;

import app.domain.ActivationNotif;
import app.domain.ReminderNotif;
import app.dto.ActivationNotifDto;
import app.dto.ReminderNotifCreateDto;
import app.dto.ReminderNotifDto;
import app.dto.ResetNotifDto;
import app.exception.NotFoundException;
import app.mapper.ReminderNotifMapper;
import app.repository.ReminderNotifRepository;
import app.service.ReminderNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Page<ReminderNotifDto> findByEmail(String email, Pageable pageable) {
        List<ReminderNotif> reminderNotifs = reminderNotifRepository.findAll();
        ArrayList<ReminderNotifDto> SelestedReminderNotifDtos = new ArrayList<>();

        for(ReminderNotif an : reminderNotifs) {
            if(an.getNotification().getClientEmail().equals(email)){
                SelestedReminderNotifDtos.add(reminderNotifMapper.reminderNotifToReminderNotifDto(an));
            }
        }

        Page<ReminderNotifDto> ActivationNotificationDtoPage = new PageImpl<>(SelestedReminderNotifDtos);
        return ActivationNotificationDtoPage;
    }

    @Override
    public Page<ReminderNotifDto> findBetweenDates(String startDate, String endDate, Pageable pageable) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate parsedEndDate = LocalDate.parse(endDate, dateTimeFormatter);

        List<ReminderNotif> activationNotifs = reminderNotifRepository.findAll();
        ArrayList<ReminderNotifDto> SelectedActivationNotifDtos = new ArrayList<>();

        for(ReminderNotif an: activationNotifs){
            LocalDate notifDate = an.getNotification().getCreationDate();
            if(notifDate.isAfter(parsedStartDate) && notifDate.isBefore(parsedEndDate)){
                SelectedActivationNotifDtos.add(reminderNotifMapper.reminderNotifToReminderNotifDto(an));
            }
        }

        Page<ReminderNotifDto> notificationDtoPage = new PageImpl<>(SelectedActivationNotifDtos);
        return notificationDtoPage;
    }
}
