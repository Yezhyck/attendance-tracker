package ua.yezhyck.attendance.tracker.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yezhyck.attendance.tracker.exception.NoSuchAbsenceReasonException;

@ControllerAdvice
public class AbsenceReasonExceptionHandler {

    @ExceptionHandler(NoSuchAbsenceReasonException.class)
    public ResponseEntity<String> handleNoSuchAbsenceReasonException(NoSuchAbsenceReasonException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}