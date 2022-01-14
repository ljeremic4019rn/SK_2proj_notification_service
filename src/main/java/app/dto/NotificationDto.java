package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

public class NotificationDto {
    private Long id;
    private String clientEmail;
    private String text;
    @JsonProperty("type")
    private NotificationTypeDto notificationTypeDto;
    private LocalDate creationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public NotificationTypeDto getNotificationTypeDto() {
        return notificationTypeDto;
    }

    public void setNotificationTypeDto(NotificationTypeDto notificationTypeDto) {
        this.notificationTypeDto = notificationTypeDto;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
