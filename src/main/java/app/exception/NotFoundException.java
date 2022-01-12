package app.exception;

import org.springframework.http.HttpStatus;

/**
 * Created on 10.01.2022. by Andrija inside package app.exception.
 */
public class NotFoundException extends CustomException{
    public NotFoundException(String message) {
        super(message, ErrorCode.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
