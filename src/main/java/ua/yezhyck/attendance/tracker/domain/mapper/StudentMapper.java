package ua.yezhyck.attendance.tracker.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.yezhyck.attendance.tracker.domain.dto.StudentDto;
import ua.yezhyck.attendance.tracker.domain.dto.editable.StudentEditableDto;
import ua.yezhyck.attendance.tracker.domain.entity.Student;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto mapToStudentDto(Student student);

    Student mapToStudent(StudentDto studentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studyClasses", ignore = true)
    @Mapping(target = "lessonStudents", ignore = true)
    Student mapToStudent(StudentEditableDto studentEditableDto);

    List<StudentDto> mapAllToStudentDtoList(List<Student> students);
}