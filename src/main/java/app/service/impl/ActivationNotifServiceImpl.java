package app.service.impl;

import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import app.mapper.ActivationNotifMapper;
import app.repository.ActivationNotifRepository;
import app.service.ActivationNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivationNotifServiceImpl implements ActivationNotifService {

    private ActivationNotifRepository activationNotifRepository;
    private ActivationNotifMapper activationNotifMapper;



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
