package ua.yezhyck.attendance.tracker.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yezhyck.attendance.tracker.exception.NoSuchAttendanceStatusException;

@ControllerAdvice
public class NoSuchAttendanceStatusExceptionHandler {

    @ExceptionHandler(NoSuchAttendanceStatusException.class)
    public ResponseEntity<String> handleNoSuchAttendanceStatusException(NoSuchAttendanceStatusException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}