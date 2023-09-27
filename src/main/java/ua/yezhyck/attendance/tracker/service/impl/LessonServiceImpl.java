package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.domain.dto.LessonDto;
import ua.yezhyck.attendance.tracker.domain.dto.editable.LessonEditableDto;
import ua.yezhyck.attendance.tracker.domain.entity.Lesson;
import ua.yezhyck.attendance.tracker.domain.entity.StudyClass;
import ua.yezhyck.attendance.tracker.exception.NoSuchLessonException;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudyClassException;
import ua.yezhyck.attendance.tracker.domain.mapper.LessonMapper;
import ua.yezhyck.attendance.tracker.repository.LessonRepository;
import ua.yezhyck.attendance.tracker.repository.StudyClassRepository;
import ua.yezhyck.attendance.tracker.service.LessonService;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonMapper lessonMapper;
    private final LessonRepository lessonRepository;
    private final StudyClassRepository studyClassRepository;

    @Autowired
    public LessonServiceImpl(LessonMapper lessonMapper, LessonRepository lessonRepository, StudyClassRepository studyClassRepository) {
        this.lessonMapper = lessonMapper;
        this.lessonRepository = lessonRepository;
        this.studyClassRepository = studyClassRepository;
    }

    @Override
    public LessonDto addLesson(LessonEditableDto lessonEditableDto) throws NoSuchStudyClassException {
        Lesson lesson = lessonMapper.mapToLesson(lessonEditableDto);

        StudyClass studyClass = studyClassRepository.findById(lessonEditableDto.getStudyClassId())
                .orElseThrow(() -> new NoSuchStudyClassException(String.format("Study class does not exist with id=%d", lessonEditableDto.getStudyClassId())));

        lesson.setStudyClass(studyClass);

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
    public LessonDto modifyLessonById(Long id, LessonEditableDto lessonEditableDto) throws NoSuchLessonException, NoSuchStudyClassException {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchLessonException(String.format("Lesson does not exist with id=%d", id)));
        StudyClass studyClass = studyClassRepository.findById(lessonEditableDto.getStudyClassId())
                .orElseThrow(() -> new NoSuchStudyClassException(String.format("Study class does not exist with id=%d", lessonEditableDto.getStudyClassId())));

        lesson.setDate(lessonEditableDto.getDate());
        lesson.setStudyClass(studyClass);

        return lessonMapper.mapToLessonDto(lessonRepository.save(lesson));
    }

    @Override
    public void removeLessonById(Long id) throws NoSuchLessonException {
        if (!lessonRepository.existsById(id)) {
            throw new NoSuchLessonException(String.format("Lesson does not exist with id=%d", id));
        }

        lessonRepository.deleteById(id);
    }
}