package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.LessonDto;
import ua.yezhyck.attendance.tracker.dto.LessonEditableDto;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    LessonDto addLesson(LessonEditableDto lessonEditableDto);

    Optional<LessonDto> getLessonById(Long id);

    List<LessonDto> getAllLessons();

    Optional<LessonDto> modifyLessonById(Long id, LessonEditableDto lessonEditableDto);

    Optional<LessonDto> addLessonStudentToLessonById(Long id, Long lessonStudentId);

    Optional<LessonDto> removeLessonStudentFromLessonById(Long id, Long lessonStudentId);

    void removeLessonById(Long id);

    boolean checkIfLessonExistsById(Long id);
}