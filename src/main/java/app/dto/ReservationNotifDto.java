package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationNotifDto {
    private Long id;
    @JsonProperty("notification")
    private NotificationDto notificationDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationDto getNotificationDto() {
        return notificationDto;
    }

    public void setNotificationDto(NotificationDto notificationDto) {
        this.notificationDto = notificationDto;
    }
}
