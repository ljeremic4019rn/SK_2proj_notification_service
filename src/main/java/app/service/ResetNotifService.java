package app.service;

import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResetNotifService {
    Page<ResetNotifDto> findAll(Pageable pageable);

    ResetNotifDto add(ResetNotifCreateDto resetNotifCreateDto);

    ResetNotifDto findById(Long id);

    void deleteById(Long id);
}
