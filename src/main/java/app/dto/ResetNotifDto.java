package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetNotifDto {
    private Long id;
    private String newEmail;
    private String newPassword;
    @JsonProperty("notification")
    private NotificationDto notificationDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public NotificationDto getNotificationDto() {
        return notificationDto;
    }

    public void setNotificationDto(NotificationDto notificationDto) {
        this.notificationDto = notificationDto;
    }
}
