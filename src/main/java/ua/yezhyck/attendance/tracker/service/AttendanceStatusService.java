package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.AttendanceStatusDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchAttendanceStatusException;

import java.util.List;
import java.util.Optional;

public interface AttendanceStatusService {

    AttendanceStatusDto addAttendanceStatus(AttendanceStatusDto attendanceStatusDto);

    Optional<AttendanceStatusDto> getAttendanceStatusById(Long id);

    List<AttendanceStatusDto> getAllAttendanceStatuses();

    AttendanceStatusDto modifyAttendanceStatusById(Long id, AttendanceStatusDto attendanceStatusDto) throws NoSuchAttendanceStatusException;

    void removeAttendanceStatusById(Long id);

    boolean checkIfAttendanceStatusExistsById(Long id);
}