package app.service.impl;

import app.domain.ReservationNotif;
import app.dto.ReservationNotifCreateDto;
import app.dto.ReservationNotifDto;
import app.exception.NotFoundException;
import app.mapper.ReservationNotifMapper;
import app.repository.ReservationNotifRepository;
import app.service.ReservationNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
