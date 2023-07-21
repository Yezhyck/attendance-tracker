package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.dto.editable.StudyClassEditableDto;
import ua.yezhyck.attendance.tracker.entity.Student;
import ua.yezhyck.attendance.tracker.entity.StudyClass;
import ua.yezhyck.attendance.tracker.entity.User;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudentException;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudyClassException;
import ua.yezhyck.attendance.tracker.exception.NoSuchUserException;
import ua.yezhyck.attendance.tracker.mapper.StudyClassMapper;
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
    private final UserRepository userRepository;

    @Autowired
    public StudyClassServiceImpl(StudyClassMapper studyClassMapper, StudyClassRepository studyClassRepository, StudentRepository studentRepository, UserRepository userRepository) {
        this.studyClassMapper = studyClassMapper;
        this.studyClassRepository = studyClassRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudyClassDto addStudyClass(StudyClassEditableDto studyClassEditableDto) throws NoSuchUserException {
        StudyClass studyClass = studyClassMapper.mapToStudyClass(studyClassEditableDto);

        User user = userRepository.findById(studyClassEditableDto.getUserId())
                .orElseThrow(() -> new NoSuchUserException(String.format("User does not exist with id=%d", studyClassEditableDto.getUserId())));

        studyClass.setUser(user);

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
    public StudyClassDto modifyStudyClassById(Long id, StudyClassEditableDto studyClassEditableDto) throws NoSuchStudyClassException, NoSuchUserException {
        StudyClass studyClass = studyClassRepository.findById(id)
                .orElseThrow(() -> new NoSuchStudyClassException(String.format("Study class does not exist with id=%d", id)));
        User user = userRepository.findById(studyClassEditableDto.getUserId())
                .orElseThrow(() -> new NoSuchUserException(String.format("User does not exist with id=%d", studyClassEditableDto.getUserId())));

        studyClass.setName(studyClassEditableDto.getName());
        studyClass.setUser(user);

        return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
    }

    @Override
    public StudyClassDto addStudentToStudyClassById(Long id, Long studentId) throws NoSuchStudyClassException, NoSuchStudentException {
        StudyClass studyClass = studyClassRepository.findById(id)
                .orElseThrow(() -> new NoSuchStudyClassException(String.format("Study class does not exist with id=%d", id)));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentException(String.format("Student does not exist with id=%d", studentId)));

        studyClass.getStudents().add(student);

        return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
    }

    @Override
    public StudyClassDto removeStudentFromStudyClassById(Long id, Long studentId) throws NoSuchStudyClassException, NoSuchStudentException {
        StudyClass studyClass = studyClassRepository.findById(id)
                .orElseThrow(() -> new NoSuchStudyClassException(String.format("Study class does not exist with id=%d", id)));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentException(String.format("Student does not exist with id=%d", studentId)));

        studyClass.getStudents().remove(student);

        return studyClassMapper.mapToStudyClassDto(studyClassRepository.save(studyClass));
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