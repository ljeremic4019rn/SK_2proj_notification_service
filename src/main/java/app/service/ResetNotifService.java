package app.service;

import app.dto.ReservationNotifDto;
import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResetNotifService {
    Page<ResetNotifDto> findAll(Pageable pageable);

    ResetNotifDto add(ResetNotifCreateDto resetNotifCreateDto);

    ResetNotifDto findById(Long id);

    void deleteById(Long id);


    Page<ResetNotifDto> findByEmail(String email, Pageable pageable);

    Page<ResetNotifDto> findBetweenDates(String startDate, String endDate, Pageable pageable);

}
