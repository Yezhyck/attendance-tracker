package ua.yezhyck.attendance.tracker.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.domain.dto.AttendanceStatusDto;
import ua.yezhyck.attendance.tracker.service.impl.AttendanceStatusServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/attendance-statuses")
public class AttendanceStatusController {
    private final AttendanceStatusServiceImpl attendanceStatusService;

    @Autowired
    public AttendanceStatusController(AttendanceStatusServiceImpl attendanceStatusService) {
        this.attendanceStatusService = attendanceStatusService;
    }

    @PostMapping
    public ResponseEntity<AttendanceStatusDto> createAttendanceStatus(@RequestBody AttendanceStatusDto attendanceStatusDto) {
        return ResponseEntity.ok(attendanceStatusService.addAttendanceStatus(attendanceStatusDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceStatusDto> readAttendanceStatusById(@PathVariable("id") Long id) {
        return attendanceStatusService.getAttendanceStatusById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AttendanceStatusDto>> readAllAttendanceStatuses() {
        return ResponseEntity.ok(attendanceStatusService.getAllAttendanceStatuses());
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceStatusDto> updateAttendanceStatusById(@PathVariable("id") Long id, @RequestBody AttendanceStatusDto attendanceStatusDto) {
        return ResponseEntity.ok(attendanceStatusService.modifyAttendanceStatusById(id, attendanceStatusDto));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendanceStatusById(@PathVariable("id") Long id) {
        attendanceStatusService.removeAttendanceStatusById(id);

        return ResponseEntity.ok().build();
    }
}