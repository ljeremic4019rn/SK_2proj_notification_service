package app.service.impl;

import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import app.service.ActivationNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ActivationNotifServiceImpl implements ActivationNotifService {
    @Override
    public Page<ActivationNotifDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ActivationNotifDto add(ActivationNotifCreateDto activationNotifCreateDto) {
        return null;
    }

    @Override
    public ActivationNotifDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
