package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.model.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.LessonStudentEditableDto;
import ua.yezhyck.attendance.tracker.exception.*;

import java.util.List;
import java.util.Optional;

public interface LessonStudentService {

    LessonStudentDto addLessonStudent(LessonStudentEditableDto lessonStudentEditableDto) throws NoSuchStudentException, NoSuchLessonException, NoSuchAbsenceReasonException, NoSuchAttendanceStatusException, NotAddedToStudyClassStudentException;

    Optional<LessonStudentDto> getLessonStudentById(Long id);

    List<LessonStudentDto> getAllLessonStudents();

    LessonStudentDto modifyLessonStudentById(Long id, LessonStudentEditableDto lessonStudentEditableDto) throws NoSuchLessonStudentException, NoSuchStudentException, NoSuchLessonException, NoSuchAbsenceReasonException, NoSuchAttendanceStatusException, NotAddedToStudyClassStudentException;

    void removeLessonStudentById(Long id) throws NoSuchLessonStudentException;
}