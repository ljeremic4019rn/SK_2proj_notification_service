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

    public ReminderNotif() {
    }

    public ReminderNotif(String timeUntilReservation, Notification notification) {
        this.timeUntilReservation = timeUntilReservation;
        this.notification = notification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeUntilReservation() {
        return timeUntilReservation;
    }

    public void setTimeUntilReservation(String timeUntilReservation) {
        this.timeUntilReservation = timeUntilReservation;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
