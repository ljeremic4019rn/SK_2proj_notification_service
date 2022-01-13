package app.service.impl;

import app.domain.ActivationNotif;
import app.domain.Notification;
import app.dto.ActivationNotifCreateDto;
import app.dto.ActivationNotifDto;
import app.exception.NotFoundException;
import app.mapper.ActivationNotifMapper;
import app.repository.ActivationNotifRepository;
import app.repository.NotificationRepository;
import app.service.ActivationNotifService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@Transactional
public class ActivationNotifServiceImpl implements ActivationNotifService {

    private ActivationNotifRepository activationNotifRepository;
    private ActivationNotifMapper activationNotifMapper;
    private NotificationRepository notificationRepository;

    private JmsTemplate jmsTemplate;
    private ObjectMapper objectMapper;
    private String destinationTestMessage;

    public ActivationNotifServiceImpl(ActivationNotifRepository activationNotifRepository, ActivationNotifMapper activationNotifMapper,
                                      NotificationRepository notificationRepository,
                                      JmsTemplate jmsTemplate, ObjectMapper objectMapper, @Value("${destination.testMessage}") String destinationTestMessage) {
        this.activationNotifRepository = activationNotifRepository;
        this.activationNotifMapper = activationNotifMapper;
        this.notificationRepository = notificationRepository;
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
        this.destinationTestMessage = destinationTestMessage;
    }

    @Override
    public Page<ActivationNotifDto> findAll(Pageable pageable) {
        return activationNotifRepository.findAll(pageable)
                .map(activationNotifMapper::activationNotifToActivationNotifDto);
    }

    @Override
    public ActivationNotifDto add(ActivationNotifCreateDto activationNotifCreateDto) {
        ActivationNotif activationNotif = activationNotifMapper.activationNotifCreateDtoToActivationNotif(activationNotifCreateDto);
        activationNotifRepository.save(activationNotif);
        return activationNotifMapper.activationNotifToActivationNotifDto(activationNotif);

    }

    @Override
    public ActivationNotifDto findById(Long id) {
        return activationNotifRepository.findById((id))
                .map(activationNotifMapper::activationNotifToActivationNotifDto)
                .orElseThrow(() -> new NotFoundException(String.format("activationNotifRepository with id: %d not found.", id)));
    }

    @Override
    public void deleteById(Long id) {
        activationNotifRepository.deleteById(id);
    }
}
