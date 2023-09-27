package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.domain.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.domain.dto.editable.StudyClassEditableDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudentException;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudyClassException;
import ua.yezhyck.attendance.tracker.exception.NoSuchUserException;

import java.util.List;
import java.util.Optional;

public interface StudyClassService {

    StudyClassDto addStudyClass(StudyClassEditableDto studyClassEditableDto) throws NoSuchUserException;

    Optional<StudyClassDto> getStudyClassById(Long id);

    List<StudyClassDto> getAllStudyClasses();

    StudyClassDto modifyStudyClassById(Long id, StudyClassEditableDto studyClassEditableDto) throws NoSuchStudyClassException, NoSuchUserException;

    StudyClassDto addStudentToStudyClassById(Long id, Long studentId) throws NoSuchStudyClassException, NoSuchStudentException;

    StudyClassDto removeStudentFromStudyClassById(Long id, Long studentId) throws NoSuchStudyClassException, NoSuchStudentException;

    void removeStudyClassById(Long id) throws NoSuchStudyClassException;
}