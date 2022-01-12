package app.service;


import app.dto.ReservationNotifCreateDto;
import app.dto.ReservationNotifDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationNotifService {
    Page<ReservationNotifDto> findAll(Pageable pageable);

    ReservationNotifDto add(ReservationNotifCreateDto reservationNotifCreateDto);

    ReservationNotifDto findById(Long id);

    void deleteById(Long id);
}
