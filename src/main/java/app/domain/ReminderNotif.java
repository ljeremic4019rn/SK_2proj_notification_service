package app.domain;

import javax.persistence.*;

@Entity
public class ReminderNotif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String timeUntilReservation;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Notification notification;

}
