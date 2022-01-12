package app.dto;

public class ReminderNotifCreateDto {
    private String timeUntilReservation;
    private String clientEmail;
    private String text;
    private String type;

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
}
