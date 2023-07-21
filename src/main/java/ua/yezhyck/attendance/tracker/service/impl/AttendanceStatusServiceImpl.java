package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.AttendanceStatusDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchAttendanceStatusException;
import ua.yezhyck.attendance.tracker.mapper.AttendanceStatusMapper;
import ua.yezhyck.attendance.tracker.repository.AttendanceStatusRepository;
import ua.yezhyck.attendance.tracker.service.AttendanceStatusService;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceStatusServiceImpl implements AttendanceStatusService {
    private final AttendanceStatusMapper attendanceStatusMapper;
    private final AttendanceStatusRepository attendanceStatusRepository;

    @Autowired
    public AttendanceStatusServiceImpl(AttendanceStatusMapper attendanceStatusMapper, AttendanceStatusRepository attendanceStatusRepository) {
        this.attendanceStatusMapper = attendanceStatusMapper;
        this.attendanceStatusRepository = attendanceStatusRepository;
    }

    @Override
    public AttendanceStatusDto addAttendanceStatus(AttendanceStatusDto attendanceStatusDto) {
        return attendanceStatusMapper.mapToAttendanceStatusDto(attendanceStatusRepository.save(attendanceStatusMapper.mapToAttendanceStatus(attendanceStatusDto)));
    }

    @Override
    public Optional<AttendanceStatusDto> getAttendanceStatusById(Long id) {
        return attendanceStatusRepository.findById(id).map(attendanceStatusMapper::mapToAttendanceStatusDto);
    }

    @Override
    public List<AttendanceStatusDto> getAllAttendanceStatuses() {
        return attendanceStatusMapper.mapAllToAttendanceStatusDtoList(attendanceStatusRepository.findAll());
    }

    @Override
    public AttendanceStatusDto modifyAttendanceStatusById(Long id, AttendanceStatusDto attendanceStatusDto) throws NoSuchAttendanceStatusException {
        return attendanceStatusRepository.findById(id)
                .map(attendanceStatus -> {
                    attendanceStatus.setName(attendanceStatusDto.getName());
                    attendanceStatus.setType(attendanceStatusDto.getType());

                    return attendanceStatusMapper.mapToAttendanceStatusDto(attendanceStatusRepository.save(attendanceStatus));
                })
                .orElseThrow(() -> new NoSuchAttendanceStatusException(String.format("Attendance status does not exist with id=%d", id)));
    }

    @Override
    public void removeAttendanceStatusById(Long id) throws NoSuchAttendanceStatusException {
        if (!attendanceStatusRepository.existsById(id)) {
            throw new NoSuchAttendanceStatusException(String.format("Attendance status does not exist with id=%d", id));
        }

        attendanceStatusRepository.deleteById(id);
    }
}