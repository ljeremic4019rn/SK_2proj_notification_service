package app.dto;

import javax.validation.constraints.NotBlank;

public class NotificationCreateDto {
    @NotBlank
    private String clientEmail;
    @NotBlank
    private String text;
    @NotBlank
    private String type;

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
