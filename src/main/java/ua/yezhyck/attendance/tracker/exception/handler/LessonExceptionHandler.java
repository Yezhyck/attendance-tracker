package ua.yezhyck.attendance.tracker.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yezhyck.attendance.tracker.exception.NoSuchLessonException;

@ControllerAdvice
public class LessonExceptionHandler {

    @ExceptionHandler(NoSuchLessonException.class)
    public ResponseEntity<String> handleNoSuchLessonException(NoSuchLessonException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}