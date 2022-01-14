package app.service.impl;

import app.domain.ActivationNotif;
import app.domain.ResetNotif;
import app.dto.ActivationNotifDto;
import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import app.exception.NotFoundException;
import app.mapper.ResetNotifMapper;
import app.repository.ResetNotifRepository;
import app.service.ResetNotifService;
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
public class ResetNotifServiceImpl implements ResetNotifService {

    private ResetNotifRepository resetNotifRepository;
    private ResetNotifMapper resetNotifMapper;

    public ResetNotifServiceImpl(ResetNotifRepository resetNotifRepository, ResetNotifMapper resetNotifMapper) {
        this.resetNotifRepository = resetNotifRepository;
        this.resetNotifMapper = resetNotifMapper;
    }

    @Override
    public Page<ResetNotifDto> findAll(Pageable pageable) {
        return resetNotifRepository.findAll(pageable)
                .map(resetNotifMapper::resetNotifToResetNotifDto);
    }

    @Override
    public ResetNotifDto add(ResetNotifCreateDto resetNotifCreateDto) {
        ResetNotif resetNotif = resetNotifMapper.resetNotifCreateDtoToResetNotif(resetNotifCreateDto);
        resetNotifRepository.save(resetNotif);
        return resetNotifMapper.resetNotifToResetNotifDto(resetNotif);
    }

    @Override
    public ResetNotifDto findById(Long id) {
        return resetNotifRepository.findById((id))
                .map(resetNotifMapper::resetNotifToResetNotifDto)
                .orElseThrow(() -> new NotFoundException(String.format("Client with id: %d not found.", id)));
    }

    @Override
    public void deleteById(Long id) {
        resetNotifRepository.deleteById((id));
    }

    @Override
    public Page<ResetNotifDto> findByEmail(String email, Pageable pageable) {
        List<ResetNotif> resetNotifs = resetNotifRepository.findAll();
        ArrayList<ResetNotifDto> SelectedResetNotifDtos = new ArrayList<>();

        for(ResetNotif an : resetNotifs) {
            if(an.getNotification().getClientEmail().equals(email)){
                SelectedResetNotifDtos.add(resetNotifMapper.resetNotifToResetNotifDto(an));
            }
        }

        Page<ResetNotifDto> ActivationNotificationDtoPage = new PageImpl<>(SelectedResetNotifDtos);
        return ActivationNotificationDtoPage;
    }

    @Override
    public Page<ResetNotifDto> findBetweenDates(String startDate, String endDate, Pageable pageable) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate parsedEndDate = LocalDate.parse(endDate, dateTimeFormatter);

        List<ResetNotif> resetNotifs = resetNotifRepository.findAll();
        ArrayList<ResetNotifDto> SelectedResetNotifDtos = new ArrayList<>();

        for(ResetNotif an: resetNotifs){
            LocalDate notifDate = an.getNotification().getCreationDate();
            if(notifDate.isAfter(parsedStartDate) && notifDate.isBefore(parsedEndDate)){
                SelectedResetNotifDtos.add(resetNotifMapper.resetNotifToResetNotifDto(an));
            }
        }

        Page<ResetNotifDto> notificationDtoPage = new PageImpl<>(SelectedResetNotifDtos);
        return notificationDtoPage;
    }
}
