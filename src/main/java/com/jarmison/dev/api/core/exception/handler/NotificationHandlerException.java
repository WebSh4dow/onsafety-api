package com.jarmison.dev.api.core.exception.handler;

import com.jarmison.dev.api.core.exception.builder.ErrorBuilder;
import com.jarmison.dev.api.core.exception.error.Error;
import com.jarmison.dev.api.core.exception.notification.NotificationException;
import com.jarmison.dev.api.core.exception.notification.NotificationNotFoundException;
import com.jarmison.dev.api.core.exception.notification.NotificationViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class NotificationHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotificationException.class})
    public ResponseEntity<List<Error>> handleNotificationException(NotificationException ex) {
        List<Error> errors = List.of((ErrorBuilder.builder()
                .withStatus(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getMessage())
                .withDetails(ex)
                .withSeverity(ex.getSeverity())
                .build()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler({NotificationNotFoundException.class})
    public ResponseEntity<List<Error>> handleNotificationNotFoundException(NotificationNotFoundException ex) {
        List<Error> errors = List.of((ErrorBuilder.builder()
                .withStatus(HttpStatus.NOT_FOUND)
                .withMessage(ex.getMessage())
                .withSeverity(ex.getSeverity())
                .withDetails(ex)
                .build()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotificationViolationException.class)
    public ResponseEntity<Error> handleNotificationViolationException(NotificationViolationException ex) {
        Error error = ErrorBuilder.builder()
                .withStatus(HttpStatus.CONFLICT)
                .withMessage(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}