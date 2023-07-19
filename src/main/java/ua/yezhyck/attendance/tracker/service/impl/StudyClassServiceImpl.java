package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.dto.editable.StudyClassEditableDto;
import ua.yezhyck.attendance.tracker.entity.StudyClass;
import ua.yezhyck.attendance.tracker.mapper.StudyClassMapper;
import ua.yezhyck.attendance.tracker.repository.LessonRepository;
import ua.yezhyck.attendance.tracker.repository.StudentRepository;
import ua.yezhyck.attendance.tracker.repository.StudyClassRepository;
import ua.yezhyck.attendance.tracker.repository.UserRepository;
import ua.yezhyck.attendance.tracker.service.StudyClassService;

import java.util.List;
import java.util.Optional;

@Service
public class StudyClassServiceImpl implements StudyClassService {
    private final StudyClassMapper studyClassMapper;
    private final StudyClassRepository studyClassRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudyClassServiceImpl(StudyClassMapper studyClassMapper, StudyClassRepository studyClassRepository,
                                 StudentRepository studentRepository,
                                 LessonRepository lessonRepository,
                                 UserRepository userRepository) {
        this.studyClassMapper = studyClassMapper;
        this.studyClassRepository = studyClassRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudyClassDto addStudyClass(StudyClassEditableDto studyClassEditableDto) {
        StudyClass studyClass = studyClassMapper.mapToStudyClass(studyClassEditableDto);

        userRepository.findById(studyClassEditableDto.getUserId())
                .ifPresent(studyClass::setUser);

        return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
    }

    @Override
    public Optional<StudyClassDto> getStudyClassById(Long id) {
        return studyClassRepository.findById(id).map(studyClassMapper::mapToStudyClassDto);
    }

    @Override
    public List<StudyClassDto> getAllStudyClasses() {
        return studyClassMapper.mapAllToStudyClassDtoList(studyClassRepository.findAll());
    }

    @Override
    public Optional<StudyClassDto> modifyStudyClassById(Long id, StudyClassEditableDto studyClassEditableDto) {
        return studyClassRepository.findById(id)
                .map(studyClass -> {
                    studyClass.setName(studyClassEditableDto.getName());

                    userRepository.findById(studyClassEditableDto.getUserId())
                            .ifPresent(studyClass::setUser);

                    return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
                });
    }

    @Override
    public Optional<StudyClassDto> addStudentToStudyClassById(Long id, Long studentId) {
        return studyClassRepository.findById(id)
                .map(studyClass -> {
                    studentRepository.findById(studentId)
                            .ifPresent(student -> studyClass.getStudents().add(student));

                    return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
                });
    }

    @Override
    public Optional<StudyClassDto> removeStudentFromStudyClassById(Long id, Long studentId) {
        return studyClassRepository.findById(id)
                .map(studyClass -> {
                    studentRepository.findById(studentId)
                            .ifPresent(student -> studyClass.getStudents().remove(student));

                    return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
                });
    }

    @Override
    public Optional<StudyClassDto> addLessonToStudyClassById(Long id, Long lessonId) {
        return studyClassRepository.findById(id)
                .map(studyClass -> {
                    lessonRepository.findById(lessonId)
                            .ifPresent(lesson -> studyClass.getLessons().add(lesson));

                    return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
                });
    }

    @Override
    public Optional<StudyClassDto> removeLessonToStudyClassById(Long id, Long lessonId) {
        return studyClassRepository.findById(id)
                .map(studyClass -> {
                    lessonRepository.findById(lessonId)
                            .ifPresent(lesson -> studyClass.getLessons().remove(lesson));

                    return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
                });
    }

    @Override
    public void removeStudyClassById(Long id) {
        studyClassRepository.deleteById(id);
    }

    @Override
    public boolean checkIfStudyClassExistsById(Long id) {
        return studyClassRepository.existsById(id);
    }
}