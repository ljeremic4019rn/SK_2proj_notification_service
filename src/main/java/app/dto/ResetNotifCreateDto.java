package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

public class ResetNotifCreateDto {

    private String resetLink;
    private String clientEmail;
    private String text;
    private String type;
//    private LocalDate creationDate;

    public String getResetLink() {
        return resetLink;
    }

    public void setResetLink(String resetLink) {
        this.resetLink = resetLink;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//
//    public LocalDate getCreationDate() {
//        return creationDate;
//    }
//
//    public void setCreationDate(LocalDate creationDate) {
//        this.creationDate = creationDate;
//    }
}
