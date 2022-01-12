package app.dto;

import javax.validation.constraints.NotBlank;

/**
 * Created on 12.01.2022. by Andrija inside package app.dto.
 */
public class NotificationTypeCreateDto {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
