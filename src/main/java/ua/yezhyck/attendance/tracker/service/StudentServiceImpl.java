package ua.yezhyck.attendance.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.StudentDto;
import ua.yezhyck.attendance.tracker.mapper.StudentMapper;
import ua.yezhyck.attendance.tracker.repository.StudentRepository;

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
    public Optional<StudentDto> modifyStudentById(Long id, StudentDto studentDto) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstName(studentDto.getFirstName());
                    student.setLastName(studentDto.getLastName());

                    return Optional.of(studentMapper.mapToStudentDto(studentRepository.save(student)));
                })
                .orElse(Optional.empty());
    }

    @Override
    public void removeStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public boolean checkIfStudentExistsById(Long id) {
        return studentRepository.existsById(id);
    }
}