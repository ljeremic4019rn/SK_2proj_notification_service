package app.service.impl;

import app.domain.ActivationNotif;
import app.domain.Notification;
import app.domain.ResetNotif;
import app.dto.ActivationNotifDto;
import app.dto.CustomNotificationDto;
import app.dto.NotificationCreateDto;
import app.dto.NotificationDto;
import app.exception.NotFoundException;
import app.mapper.CustomNotificationMapper;
import app.mapper.NotificationMapper;
import app.repository.ActivationNotifRepository;
import app.repository.NotificationRepository;
import app.repository.ResetNotifRepository;
import app.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;
    private ActivationNotifRepository activationNotifRepository;
    private ResetNotifRepository resetNotifRepository;
    private CustomNotificationMapper customNotificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper,
                                   ActivationNotifRepository activationNotifRepository, ResetNotifRepository resetNotifRepository, CustomNotificationMapper customNotificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.activationNotifRepository = activationNotifRepository;
        this.resetNotifRepository = resetNotifRepository;
        this.customNotificationMapper = customNotificationMapper;
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::notificationToNotificationDto);
    }

    @Override
    public NotificationDto add(NotificationCreateDto notificationCreateDto) {
        Notification notification = notificationMapper.notificationCreateDtoToNotification(notificationCreateDto);
        notificationRepository.save(notification);
        return notificationMapper.notificationToNotificationDto(notification);
    }

    @Override
    public NotificationDto findById(Long id) {
        return notificationRepository.findById((id))
                .map(notificationMapper::notificationToNotificationDto)
                .orElseThrow(() -> new NotFoundException(String.format("notificationRepository with id: %d not found.", id)));
    }

    @Override
    public void deleteById(Long id) {
        notificationRepository.deleteById((id));
    }



    @Override
    public Page<NotificationDto> findByType(String type, Pageable pageable) {
        List<Notification> notifications = notificationRepository.findAll();
        ArrayList<NotificationDto> notificationDtos = new ArrayList<>();

        for(Notification an: notifications){
            if(an.getNotificationType().getName().equals(type)){
                notificationDtos.add(notificationMapper.notificationToNotificationDto(an));
            }
        }

        Page<NotificationDto> notificationDtoPage = new PageImpl<>(notificationDtos);
        return notificationDtoPage;
    }

    @Override
    public Page<CustomNotificationDto> findNotificationsByName(String email, Pageable pageable) {

        System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOOO");
        List<CustomNotificationDto> customNotificationDtoList = new ArrayList<>();

        List<ActivationNotif> activationNotifs = activationNotifRepository.findActivationNotifsByNotification_ClientEmail(email);
//        if (activationNotifs.isEmpty()){
//            System.out.println("YEEEEEES EMPTYYYYY");
//        }
        for (ActivationNotif a:activationNotifs) {
//            System.out.println(a.getActivationLink());
                customNotificationDtoList.add(customNotificationMapper.activationNotifToCustomNotificationDto(a));
        }

        List<ResetNotif> resetNotifs = resetNotifRepository.findResetNotifsByNotification_ClientEmail(email);
        for (ResetNotif r:resetNotifs) {
            customNotificationDtoList.add(customNotificationMapper.resetNotifToCustomNotificationDto(r));
        }
        for (CustomNotificationDto d:customNotificationDtoList) {
            System.out.println(d.getEmail()+" "+d.getText());
        }

//
//        for (CustomNotificationDto c:customNotificationDtoList) {
//            System.out.println(String.format("{%s %s %s}", c.getEmail(),c.getText(),c.getType()));
//        }
        Page<CustomNotificationDto> customNotificationDtoPage = new PageImpl<>(customNotificationDtoList);
        return customNotificationDtoPage;
//        return notificationRepository.findAll(pageable)
//                .map(notificationMapper::notificationToNotificationDto);
    }
}
