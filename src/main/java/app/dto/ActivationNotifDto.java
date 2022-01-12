package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivationNotifDto {
    private Long id;
    private String activationLink;
    @JsonProperty("notification")
    private NotificationDto notificationDto;


}
