package app.service.impl;

import app.dto.ReservationNotifCreateDto;
import app.dto.ReservationNotifDto;
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
        return null;
    }

    @Override
    public ReservationNotifDto add(ReservationNotifCreateDto reservationNotifCreateDto) {
        return null;
    }

    @Override
    public ReservationNotifDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
