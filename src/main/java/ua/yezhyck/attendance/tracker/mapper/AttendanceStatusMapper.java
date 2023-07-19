package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.yezhyck.attendance.tracker.dto.AttendanceStatusDto;
import ua.yezhyck.attendance.tracker.dto.editable.AttendanceStatusEditableDto;
import ua.yezhyck.attendance.tracker.entity.AttendanceStatus;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceStatusMapper {

    AttendanceStatusDto mapToAttendanceStatusDto(AttendanceStatus attendanceStatus);

    AttendanceStatus mapToAttendanceStatus(AttendanceStatusDto attendanceStatusDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lessonStudents", ignore = true)
    AttendanceStatus mapToAttendanceStatus(AttendanceStatusEditableDto attendanceStatusEditableDto);

    List<AttendanceStatusDto> mapAllToAttendanceStatusDtoList(List<AttendanceStatus> attendanceStatuses);
}