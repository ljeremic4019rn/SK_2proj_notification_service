package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetNotifDto {
    private Long id;
    private String resetLink;
    @JsonProperty("notification")
    private NotificationDto notificationDto;

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

    public NotificationDto getNotificationDto() {
        return notificationDto;
    }

    public void setNotificationDto(NotificationDto notificationDto) {
        this.notificationDto = notificationDto;
    }
}
