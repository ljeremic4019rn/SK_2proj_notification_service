package app.domain;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class ReservationNotif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //todo dodaj varijablu za rezervaciju
    //todo dodatni emails za menagere

    @OneToOne(cascade = CascadeType.REMOVE)
    private Notification notification;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
