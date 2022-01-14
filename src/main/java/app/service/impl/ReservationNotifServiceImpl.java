package app.service.impl;

import app.domain.ActivationNotif;
import app.domain.ReservationNotif;
import app.dto.ActivationNotifDto;
import app.dto.ReservationNotifCreateDto;
import app.dto.ReservationNotifDto;
import app.exception.NotFoundException;
import app.mapper.ReservationNotifMapper;
import app.repository.ReservationNotifRepository;
import app.service.ReservationNotifService;
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
public class ReservationNotifServiceImpl implements ReservationNotifService {

   private ReservationNotifRepository reservationNotifRepository;
   private ReservationNotifMapper reservationNotifMapper;

    public ReservationNotifServiceImpl(ReservationNotifRepository reservationNotifRepository, ReservationNotifMapper reservationNotifMapper) {
        this.reservationNotifRepository = reservationNotifRepository;
        this.reservationNotifMapper = reservationNotifMapper;
    }

    @Override
    public Page<ReservationNotifDto> findAll(Pageable pageable) {
        return reservationNotifRepository.findAll(pageable)
                .map(reservationNotifMapper::reservationNotifToReservationNotifDto);
    }

    @Override
    public ReservationNotifDto add(ReservationNotifCreateDto reservationNotifCreateDto) {
        ReservationNotif reservationNotif = reservationNotifMapper.reservationNotifCreateDtoToReservationNotif(reservationNotifCreateDto);
        reservationNotifRepository.save(reservationNotif);
        return reservationNotifMapper.reservationNotifToReservationNotifDto(reservationNotif);
    }

    @Override
    public ReservationNotifDto findById(Long id) {
        return reservationNotifRepository.findById((id))
                .map(reservationNotifMapper::reservationNotifToReservationNotifDto)
                .orElseThrow(() -> new NotFoundException(String.format("Client with id: %d not found.", id)));
    }

    @Override
    public void deleteById(Long id) {
        reservationNotifRepository.deleteById((id));
    }

    @Override
    public Page<ReservationNotifDto> findByEmail(String email, Pageable pageable) {
        List<ReservationNotif> reservationNotifs = reservationNotifRepository.findAll();
        ArrayList<ReservationNotifDto> SelectedReservationNotifDtos = new ArrayList<>();

        for(ReservationNotif an : reservationNotifs) {
            if(an.getNotification().getClientEmail().equals(email)){
                SelectedReservationNotifDtos.add(reservationNotifMapper.reservationNotifToReservationNotifDto(an));
            }
        }

        Page<ReservationNotifDto> ActivationNotificationDtoPage = new PageImpl<>(SelectedReservationNotifDtos);
        return ActivationNotificationDtoPage;
    }

    @Override
    public Page<ReservationNotifDto> findBetweenDates(String startDate, String endDate, Pageable pageable) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startDate, dateTimeFormatter);
        LocalDate parsedEndDate = LocalDate.parse(endDate, dateTimeFormatter);

        List<ReservationNotif> reservationNotifs = reservationNotifRepository.findAll();
        ArrayList<ReservationNotifDto> SelectedReservationNotifDtos = new ArrayList<>();

        for(ReservationNotif an: reservationNotifs){
            LocalDate notifDate = an.getNotification().getCreationDate();
            if(notifDate.isAfter(parsedStartDate) && notifDate.isBefore(parsedEndDate)){
                SelectedReservationNotifDtos.add(reservationNotifMapper.reservationNotifToReservationNotifDto(an));
            }
        }

        Page<ReservationNotifDto> notificationDtoPage = new PageImpl<>(SelectedReservationNotifDtos);
        return notificationDtoPage;
    }
}
