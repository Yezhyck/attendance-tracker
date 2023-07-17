package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.yezhyck.attendance.tracker.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.dto.LessonStudentEditableDto;
import ua.yezhyck.attendance.tracker.entity.LessonStudent;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, AttendanceStatusMapper.class, AbsenceReasonMapper.class})
public interface LessonStudentMapper {
    LessonStudentMapper INSTANCE = Mappers.getMapper(LessonStudentMapper.class);

    @Mapping(source = "student", target = "studentDto")
    @Mapping(source = "attendanceStatus", target = "attendanceStatusDto")
    @Mapping(source = "absenceReason", target = "absenceReasonDto")
    LessonStudentDto mapToLessonStudentDto(LessonStudent lessonStudent);

    @Mapping(source = "studentDto", target = "student")
    @Mapping(source = "attendanceStatusDto", target = "attendanceStatus")
    @Mapping(source = "absenceReasonDto", target = "absenceReason")
    LessonStudent mapToLessonStudent(LessonStudentDto lessonStudentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "attendanceStatus", ignore = true)
    @Mapping(target = "absenceReason", ignore = true)
    LessonStudent mapToLessonStudent(LessonStudentEditableDto lessonStudentEditableDto);

    List<LessonStudentDto> mapAllToLessonStudentDtoList(List<LessonStudent> lessonStudents);

    List<LessonStudent> mapAllToLessonStudents(List<LessonStudentDto> lessonStudentDtoList);
}