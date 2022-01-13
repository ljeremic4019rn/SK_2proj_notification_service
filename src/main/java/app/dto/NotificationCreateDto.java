package app.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

public class NotificationCreateDto {
    @NotBlank
    private String clientEmail;
    @NotBlank
    private String text;
    @NotBlank
    private String type;
    @NotBlank
    private LocalDate creationDate;


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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
