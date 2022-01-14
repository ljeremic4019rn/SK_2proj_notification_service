package app.service;

import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivationNotifService {

    Page<ActivationNotifDto> findAll(Pageable pageable);

    ActivationNotifDto add(ActivationNotifCreateDto activationNotifCreateDto);

    ActivationNotifDto findById(Long id);

    void deleteById(Long id);

    Page<ActivationNotifDto> findByEmail(String email, Pageable pageable);

    Page<ActivationNotifDto> findBetweenDates(String startDate, String endDate, Pageable pageable);

}
