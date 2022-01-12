package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivationNotifDto {
    private Long id;
    private String activationLink;
    @JsonProperty("notification")
    private NotificationDto notificationDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivationLink() {
        return activationLink;
    }

    public void setActivationLink(String activationLink) {
        this.activationLink = activationLink;
    }

    public NotificationDto getNotificationDto() {
        return notificationDto;
    }

    public void setNotificationDto(NotificationDto notificationDto) {
        this.notificationDto = notificationDto;
    }
}
