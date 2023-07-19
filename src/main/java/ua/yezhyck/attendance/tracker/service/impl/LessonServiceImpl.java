package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.LessonDto;
import ua.yezhyck.attendance.tracker.dto.editable.LessonEditableDto;
import ua.yezhyck.attendance.tracker.entity.Lesson;
import ua.yezhyck.attendance.tracker.mapper.LessonMapper;
import ua.yezhyck.attendance.tracker.repository.LessonRepository;
import ua.yezhyck.attendance.tracker.repository.LessonStudentRepository;
import ua.yezhyck.attendance.tracker.repository.StudyClassRepository;
import ua.yezhyck.attendance.tracker.service.LessonService;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonMapper lessonMapper;
    private final LessonRepository lessonRepository;
    private final StudyClassRepository studyClassRepository;
    private final LessonStudentRepository lessonStudentRepository;

    @Autowired
    public LessonServiceImpl(LessonMapper lessonMapper, LessonRepository lessonRepository, StudyClassRepository studyClassRepository,
                             LessonStudentRepository lessonStudentRepository) {
        this.lessonMapper = lessonMapper;
        this.lessonRepository = lessonRepository;
        this.studyClassRepository = studyClassRepository;
        this.lessonStudentRepository = lessonStudentRepository;
    }

    @Override
    public LessonDto addLesson(LessonEditableDto lessonEditableDto) {
        Lesson lesson = lessonMapper.mapToLesson(lessonEditableDto);

        studyClassRepository.findById(lessonEditableDto.getStudyClassId())
                .ifPresent(lesson::setStudyClass);

        return lessonMapper.mapToLessonDto(lessonRepository.save(lesson));
    }

    @Override
    public Optional<LessonDto> getLessonById(Long id) {
        return lessonRepository.findById(id).map(lessonMapper::mapToLessonDto);
    }

    @Override
    public List<LessonDto> getAllLessons() {
        return lessonMapper.mapAllToLessonDtoList(lessonRepository.findAll());
    }

    @Override
    public Optional<LessonDto> modifyLessonById(Long id, LessonEditableDto lessonEditableDto) {
        return lessonRepository.findById(id)
                .map(lesson -> {
                    lesson.setDate(lessonEditableDto.getDate());

                    studyClassRepository.findById(lessonEditableDto.getStudyClassId())
                            .ifPresent(lesson::setStudyClass);

                    return lessonMapper.mapToLessonDto(lessonRepository.save(lesson));
                });
    }

    @Override
    public Optional<LessonDto> addLessonStudentToLessonById(Long id, Long lessonStudentId) {
        return lessonRepository.findById(id)
                .map(lesson -> {
                    lessonStudentRepository.findById(lessonStudentId)
                            .ifPresent(lessonStudent -> lesson.getLessonStudents().add(lessonStudent));

                    return lessonMapper.mapToLessonDto(lessonRepository.save(lesson));
                });
    }

    @Override
    public Optional<LessonDto> removeLessonStudentFromLessonById(Long id, Long lessonStudentId) {
        return lessonRepository.findById(id)
                .map(lesson -> {
                    lessonStudentRepository.findById(lessonStudentId)
                            .ifPresent(lessonStudent -> lesson.getLessonStudents().remove(lessonStudent));

                    return lessonMapper.mapToLessonDto(lessonRepository.save(lesson));
                });
    }

    @Override
    public void removeLessonById(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public boolean checkIfLessonExistsById(Long id) {
        return lessonRepository.existsById(id);
    }
}