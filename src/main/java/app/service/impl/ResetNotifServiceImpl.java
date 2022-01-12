package app.service.impl;

import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import app.mapper.ResetNotifMapper;
import app.repository.ResetNotifRepository;
import app.service.ResetNotifService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResetNotifServiceImpl implements ResetNotifService {

    private ResetNotifRepository resetNotifRepository;
    private ResetNotifMapper resetNotifMapper;

    public ResetNotifServiceImpl(ResetNotifRepository resetNotifRepository, ResetNotifMapper resetNotifMapper) {
        this.resetNotifRepository = resetNotifRepository;
        this.resetNotifMapper = resetNotifMapper;
    }

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
