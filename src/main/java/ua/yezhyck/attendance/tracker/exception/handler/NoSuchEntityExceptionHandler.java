package ua.yezhyck.attendance.tracker.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yezhyck.attendance.tracker.exception.*;

@ControllerAdvice
public class NoSuchEntityExceptionHandler {

    @ExceptionHandler(NoSuchAbsenceReasonException.class)
    public ResponseEntity<String> handleNoSuchAbsenceReasonException(NoSuchAbsenceReasonException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchAttendanceStatusException.class)
    public ResponseEntity<String> handleNoSuchAttendanceStatusException(NoSuchAttendanceStatusException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchLessonException.class)
    public ResponseEntity<String> handleNoSuchLessonException(NoSuchLessonException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchLessonStudentException.class)
    public ResponseEntity<String> handleNoSuchLessonStudentException(NoSuchLessonStudentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchStudentException.class)
    public ResponseEntity<String> handleNoSuchStudentException(NoSuchStudentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchStudyClassException.class)
    public ResponseEntity<String> handleNoSuchStudyClassException(NoSuchStudyClassException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<String> handleNoSuchUserException(NoSuchUserException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}