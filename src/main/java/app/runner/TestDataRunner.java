package app.runner;

import app.domain.*;
import app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    NotificationTypeRepository notificationTypeRepository;
    NotificationRepository notificationRepository;
    ActivationNotifRepository activationNotifRepository;
    ReminderNotifRepository reminderNotifRepository;
    ReservationNotifRepository reservationNotifRepository;
    ResetNotifRepository resetNotifRepository;

    public TestDataRunner(NotificationTypeRepository notificationTypeRepository, NotificationRepository notificationRepository,
                          ActivationNotifRepository activationNotifRepository, ReminderNotifRepository reminderNotifRepository,
                          ReservationNotifRepository reservationNotifRepository, ResetNotifRepository resetNotifRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRepository = notificationRepository;
        this.activationNotifRepository = activationNotifRepository;
        this.reminderNotifRepository = reminderNotifRepository;
        this.reservationNotifRepository = reservationNotifRepository;
        this.resetNotifRepository = resetNotifRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert NotificationType
        NotificationType activation = new NotificationType("activation");
        NotificationType reminder = new NotificationType("reminder");
        NotificationType reservation = new NotificationType("reservation");
        NotificationType reset = new NotificationType("reset");

        notificationTypeRepository.save(activation);
        notificationTypeRepository.save(reminder);
        notificationTypeRepository.save(reservation);
        notificationTypeRepository.save(reset);

        Notification notification1 = new Notification("narbutina@gmail.com","Zdravo Andrija", activation);
        Notification notification2 = new Notification("narbutina@gmail.com","Zdravo Luka", reminder);
        Notification notification3 = new Notification("narbutina@gmail.com","Zdravo Nenade", reservation);
        Notification notification4 = new Notification("narbutina@gmail.com","Zdravo Petre", reset);

        notificationRepository.save(notification1);
        notificationRepository.save(notification2);
        notificationRepository.save(notification3);
        notificationRepository.save(notification4);

        ActivationNotif activationNotif1 = new ActivationNotif("dingdingactivacionilinkrazdvatrikalinka", notification1);
        ReminderNotif reminderNotif = new ReminderNotif("20min", notification2);
        ReservationNotif reservationNotif = new ReservationNotif(notification3);
        ResetNotif resetNotif = new ResetNotif("Link ding ding",notification4);
        activationNotifRepository.save(activationNotif1);
        reminderNotifRepository.save(reminderNotif);
        reservationNotifRepository.save(reservationNotif);
        resetNotifRepository.save(resetNotif);


    }
}
