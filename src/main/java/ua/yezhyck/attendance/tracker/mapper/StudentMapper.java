package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.yezhyck.attendance.tracker.dto.StudentDto;
import ua.yezhyck.attendance.tracker.entity.Student;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDto mapToStudentDto(Student student);

    Student mapToStudent(StudentDto studentDto);

    List<StudentDto> mapAllToStudentDtoList(List<Student> students);

    List<Student> mapAllToStudents(List<StudentDto> studentDtoList);
}