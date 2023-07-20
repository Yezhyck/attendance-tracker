package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.LessonDto;
import ua.yezhyck.attendance.tracker.dto.editable.LessonEditableDto;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    LessonDto addLesson(LessonEditableDto lessonEditableDto);

    Optional<LessonDto> getLessonById(Long id);

    List<LessonDto> getAllLessons();

    Optional<LessonDto> modifyLessonById(Long id, LessonEditableDto lessonEditableDto);

    void removeLessonById(Long id);

    boolean checkIfLessonExistsById(Long id);
}