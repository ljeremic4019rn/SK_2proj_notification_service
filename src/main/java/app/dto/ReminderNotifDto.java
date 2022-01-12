package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReminderNotifDto {
    private Long id;
    private String timeUntilReservation;
    @JsonProperty("notification")
    private NotificationDto notificationDto;

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

    public NotificationDto getNotificationDto() {
        return notificationDto;
    }

    public void setNotificationDto(NotificationDto notificationDto) {
        this.notificationDto = notificationDto;
    }
}
