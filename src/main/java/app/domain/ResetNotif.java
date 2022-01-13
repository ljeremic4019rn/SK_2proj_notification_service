package app.domain;

import javax.persistence.*;

@Entity
public class ResetNotif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String resetLink;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Notification notification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResetLink() {
        return resetLink;
    }

    public void setResetLink(String resetLink) {
        this.resetLink = resetLink;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
