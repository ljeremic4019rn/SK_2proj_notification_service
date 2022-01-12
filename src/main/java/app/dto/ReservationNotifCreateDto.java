package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationNotifCreateDto {

    private Long notificationId;

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }
}
