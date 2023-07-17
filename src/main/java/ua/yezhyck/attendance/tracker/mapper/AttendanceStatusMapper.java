package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.yezhyck.attendance.tracker.dto.AttendanceStatusDto;
import ua.yezhyck.attendance.tracker.entity.AttendanceStatus;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendanceStatusMapper {
    AttendanceStatusMapper INSTANCE = Mappers.getMapper(AttendanceStatusMapper.class);

    AttendanceStatusDto mapToAttendanceStatusDto(AttendanceStatus attendanceStatus);

    AttendanceStatus mapToAttendanceStatus(AttendanceStatusDto attendanceStatusDto);

    List<AttendanceStatusDto> mapAllToAttendanceStatusDtoList(List<AttendanceStatus> attendanceStatuses);

    List<AttendanceStatus> mapAllToAttendanceStatuses(List<AttendanceStatusDto> dtoList);
}