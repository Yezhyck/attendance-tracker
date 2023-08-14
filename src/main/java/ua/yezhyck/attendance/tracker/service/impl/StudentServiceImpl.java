package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.model.dto.StudentDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudentException;
import ua.yezhyck.attendance.tracker.model.mapper.StudentMapper;
import ua.yezhyck.attendance.tracker.repository.StudentRepository;
import ua.yezhyck.attendance.tracker.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        return studentMapper.mapToStudentDto(studentRepository.save(studentMapper.mapToStudent(studentDto)));
    }

    @Override
    public Optional<StudentDto> getStudentById(Long id) {
        return studentRepository.findById(id).map(studentMapper::mapToStudentDto);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentMapper.mapAllToStudentDtoList(studentRepository.findAll());
    }

    @Override
    public StudentDto modifyStudentById(Long id, StudentDto studentDto) throws NoSuchStudentException {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstName(studentDto.getFirstName());
                    student.setLastName(studentDto.getLastName());

                    return studentMapper.mapToStudentDto(studentRepository.save(student));
                })
                .orElseThrow(() -> new NoSuchStudentException(String.format("Student does not exist with id=%d", id)));
    }

    @Override
    public void removeStudentById(Long id) throws NoSuchStudentException {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchStudentException(String.format("Student does not exist with id=%d", id));
        }

        studentRepository.deleteById(id);
    }
}