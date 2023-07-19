package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.yezhyck.attendance.tracker.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.entity.LessonStudent;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, AttendanceStatusMapper.class, AbsenceReasonMapper.class})
public interface LessonStudentMapper {

    @Mapping(source = "student", target = "studentDto")
    @Mapping(source = "attendanceStatus", target = "attendanceStatusDto")
    @Mapping(source = "absenceReason", target = "absenceReasonDto")
    LessonStudentDto mapToLessonStudentDto(LessonStudent lessonStudent);

    @Mapping(source = "studentDto", target = "student")
    @Mapping(source = "attendanceStatusDto", target = "attendanceStatus")
    @Mapping(source = "absenceReasonDto", target = "absenceReason")
    LessonStudent mapToLessonStudent(LessonStudentDto lessonStudentDto);

    List<LessonStudentDto> mapAllToLessonStudentDtoList(List<LessonStudent> lessonStudents);
}