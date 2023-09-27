package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.domain.dto.StudentDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchStudentException;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    Optional<StudentDto> getStudentById(Long id);

    List<StudentDto> getAllStudents();

    StudentDto modifyStudentById(Long id, StudentDto studentDto) throws NoSuchStudentException;

    void removeStudentById(Long id) throws NoSuchStudentException;
}