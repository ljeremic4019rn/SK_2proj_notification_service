package app.domain;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class ReservationNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //todo dodaj varijablu za rezervaciju
    //todo dodatni emails za menagere

    @OneToOne(cascade = CascadeType.REMOVE)
    private Notification notification;

}
