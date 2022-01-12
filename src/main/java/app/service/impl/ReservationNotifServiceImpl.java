package app.service.impl;

import app.dto.ReservationNotifCreateDto;
import app.dto.ReservationNotifDto;
import app.service.ReservationNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ReservationNotifServiceImpl implements ReservationNotifService {
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
