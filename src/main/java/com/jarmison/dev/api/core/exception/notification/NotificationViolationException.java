package com.jarmison.dev.api.core.exception.notification;

import com.jarmison.dev.api.core.exception.severity.Severity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotificationViolationException extends RuntimeException {
    private final Severity severity;

    public NotificationViolationException(String message) {
        super(message);
        this.severity = Severity.ERROR;
    }

    public NotificationViolationException(String message,final Severity severity) {
        super(message);
        this.severity = severity;
    }
}
