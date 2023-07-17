package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    Optional<StudentDto> getStudentById(Long id);

    List<StudentDto> getAllStudents();

    Optional<StudentDto> modifyStudentById(Long id, StudentDto studentDto);

    void removeStudentById(Long id);

    boolean checkIfStudentExistsById(Long id);
}