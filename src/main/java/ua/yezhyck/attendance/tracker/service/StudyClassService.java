package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.dto.editable.StudyClassEditableDto;

import java.util.List;
import java.util.Optional;

public interface StudyClassService {

    StudyClassDto addStudyClass(StudyClassEditableDto studyClassEditableDto);

    Optional<StudyClassDto> getStudyClassById(Long id);

    List<StudyClassDto> getAllStudyClasses();

    Optional<StudyClassDto> modifyStudyClassById(Long id, StudyClassEditableDto studyClassEditableDto);

    Optional<StudyClassDto> addStudentToStudyClassById(Long id, Long studentId);

    Optional<StudyClassDto> removeStudentFromStudyClassById(Long id, Long studentId);

    Optional<StudyClassDto> addLessonToStudyClassById(Long id, Long lessonId);

    Optional<StudyClassDto> removeLessonFromStudyClassById(Long id, Long lessonId);

    void removeStudyClassById(Long id);

    boolean checkIfStudyClassExistsById(Long id);
}