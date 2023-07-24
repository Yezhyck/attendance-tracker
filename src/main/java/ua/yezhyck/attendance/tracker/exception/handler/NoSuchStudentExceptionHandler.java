package ua.yezhyck.attendance.tracker.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudentException;

@ControllerAdvice
public class NoSuchStudentExceptionHandler {

    @ExceptionHandler(NoSuchStudentException.class)
    public ResponseEntity<String> handleNoSuchStudentException(NoSuchStudentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}