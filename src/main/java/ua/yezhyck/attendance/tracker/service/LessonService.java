package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.model.dto.LessonDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.LessonEditableDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchLessonException;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudyClassException;

import java.util.List;
import java.util.Optional;

public interface LessonService {

    LessonDto addLesson(LessonEditableDto lessonEditableDto) throws NoSuchStudyClassException;

    Optional<LessonDto> getLessonById(Long id);

    List<LessonDto> getAllLessons();

    LessonDto modifyLessonById(Long id, LessonEditableDto lessonEditableDto) throws NoSuchLessonException, NoSuchStudyClassException;

    void removeLessonById(Long id) throws NoSuchLessonException;
}