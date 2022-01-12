package app.service.impl;

import app.domain.ResetNotif;
import app.dto.ResetNotifCreateDto;
import app.dto.ResetNotifDto;
import app.exception.NotFoundException;
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
        return resetNotifRepository.findAll(pageable)
                .map(resetNotifMapper::resetNotifToResetNotifDto);
    }

    @Override
    public ResetNotifDto add(ResetNotifCreateDto resetNotifCreateDto) {
        ResetNotif resetNotif = resetNotifMapper.resetNotifCreateDtoToResetNotif(resetNotifCreateDto);
        resetNotifRepository.save(resetNotif);
        return resetNotifMapper.resetNotifToResetNotifDto(resetNotif);
    }

    @Override
    public ResetNotifDto findById(Long id) {
        return resetNotifRepository.findById((id))
                .map(resetNotifMapper::resetNotifToResetNotifDto)
                .orElseThrow(() -> new NotFoundException(String.format("Client with id: %d not found.", id)));
    }

    @Override
    public void deleteById(Long id) {
        resetNotifRepository.deleteById((id));
    }
}
