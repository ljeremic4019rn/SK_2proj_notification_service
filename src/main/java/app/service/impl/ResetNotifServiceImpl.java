package app.service.impl;

import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import app.service.ResetNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ResetNotifServiceImpl implements ResetNotifService {
    @Override
    public Page<ResetNotifDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResetNotifDto add(ResetNotifCreateDto resetNotifCreateDto) {
        return null;
    }

    @Override
    public ResetNotifDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
