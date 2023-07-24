package ua.yezhyck.attendance.tracker.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudyClassException;

@ControllerAdvice
public class NoSuchStudyClassExceptionHandler {

    @ExceptionHandler(NoSuchStudyClassException.class)
    public ResponseEntity<String> handleNoSuchStudyClassException(NoSuchStudyClassException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}