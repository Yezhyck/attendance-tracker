package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.dto.LessonStudentEditableDto;

import java.util.List;
import java.util.Optional;

public interface LessonStudentService {

    LessonStudentDto addLessonStudent(LessonStudentEditableDto lessonStudentEditableDto);

    Optional<LessonStudentDto> getLessonStudentById(Long id);

    List<LessonStudentDto> getAllLessonStudents();

    Optional<LessonStudentDto> modifyLessonStudentById(Long id, LessonStudentEditableDto lessonStudentEditableDto);

    void removeLessonStudentById(Long id);

    boolean checkIfLessonStudentExistsById(Long id);
}