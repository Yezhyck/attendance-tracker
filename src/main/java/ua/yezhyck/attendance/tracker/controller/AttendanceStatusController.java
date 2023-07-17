package ua.yezhyck.attendance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.AttendanceStatusDto;
import ua.yezhyck.attendance.tracker.service.AttendanceStatusServiceImpl;

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
    public @ResponseBody ResponseEntity<AttendanceStatusDto> createAttendanceStatus(@RequestBody AttendanceStatusDto attendanceStatusDto) {
        return ResponseEntity.ok(attendanceStatusService.addAttendanceStatus(attendanceStatusDto));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<AttendanceStatusDto> readAttendanceStatusById(@PathVariable("id") Long id) {
        return attendanceStatusService.getAttendanceStatusById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<AttendanceStatusDto>> readAllAttendanceStatuses() {
        return ResponseEntity.ok(attendanceStatusService.getAllAttendanceStatuses());
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<AttendanceStatusDto> updateAttendanceStatusById(@PathVariable("id") Long id, @RequestBody AttendanceStatusDto attendanceStatusDto) {
        return attendanceStatusService.modifyAttendanceStatusById(id, attendanceStatusDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<Void> deleteAttendanceStatusById(@PathVariable("id") Long id) {
        if (!attendanceStatusService.checkIfAttendanceStatusExistsById(id)) {
            return ResponseEntity.notFound().build();
        }

        attendanceStatusService.removeAttendanceStatusById(id);

        return ResponseEntity.ok().build();
    }
}