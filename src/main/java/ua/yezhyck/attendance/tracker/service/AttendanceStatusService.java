package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.AttendanceStatusDto;

import java.util.List;
import java.util.Optional;

public interface AttendanceStatusService {

    AttendanceStatusDto addAttendanceStatus(AttendanceStatusDto attendanceStatusDto);

    Optional<AttendanceStatusDto> getAttendanceStatusById(Long id);

    List<AttendanceStatusDto> getAllAttendanceStatuses();

    Optional<AttendanceStatusDto> modifyAttendanceStatusById(Long id, AttendanceStatusDto attendanceStatusDto);

    void removeAttendanceStatusById(Long id);

    boolean checkIfAttendanceStatusExistsById(Long id);
}