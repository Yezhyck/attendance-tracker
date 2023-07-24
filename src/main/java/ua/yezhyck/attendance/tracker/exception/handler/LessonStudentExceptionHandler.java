package ua.yezhyck.attendance.tracker.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.yezhyck.attendance.tracker.exception.NoSuchLessonStudentException;
import ua.yezhyck.attendance.tracker.exception.NotAddedToStudyClassStudentException;

@ControllerAdvice
public class LessonStudentExceptionHandler {

    @ExceptionHandler(NoSuchLessonStudentException.class)
    public ResponseEntity<String> handleNoSuchLessonStudentException(NoSuchLessonStudentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(NotAddedToStudyClassStudentException.class)
    public ResponseEntity<String> handleNotAddedToStudyClassStudentException(NotAddedToStudyClassStudentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}