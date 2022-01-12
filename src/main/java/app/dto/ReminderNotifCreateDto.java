package app.dto;

import java.util.Date;

public class ReminderNotifCreateDto {
    private String timeUntilReservation;
    private String clientEmail;
    private String text;
    private String type;
    private Date creationDate;

    public String getTimeUntilReservation() {
        return timeUntilReservation;
    }

    public void setTimeUntilReservation(String timeUntilReservation) {
        this.timeUntilReservation = timeUntilReservation;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
