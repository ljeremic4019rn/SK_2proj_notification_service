package app.service;

import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivationNotifService {

    Page<ActivationNotifDto> findAll(Pageable pageable);

    void add(ActivationNotifCreateDto activationNotifCreateDto);

    ActivationNotifDto findById(Long id);

    void deleteById(Long id);
}
