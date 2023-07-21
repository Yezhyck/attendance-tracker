package ua.yezhyck.attendance.tracker.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.AbsenceReasonDto;
import ua.yezhyck.attendance.tracker.service.impl.AbsenceReasonServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/absence-reasons")
public class AbsenceReasonController {
    private final AbsenceReasonServiceImpl absenceReasonService;

    @Autowired
    public AbsenceReasonController(AbsenceReasonServiceImpl absenceReasonService) {
        this.absenceReasonService = absenceReasonService;
    }

    @PostMapping
    public ResponseEntity<AbsenceReasonDto> createAbsenceReason(@RequestBody AbsenceReasonDto absenceReasonDto) {
        return ResponseEntity.ok(absenceReasonService.addAbsenceReason(absenceReasonDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbsenceReasonDto> readAbsenceReasonById(@PathVariable("id") Long id) {
        return absenceReasonService.getAbsenceReasonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AbsenceReasonDto>> readAllAbsenceReasons() {
        return ResponseEntity.ok(absenceReasonService.getAllAbsenceReasons());
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<AbsenceReasonDto> updateAbsenceReasonById(@PathVariable("id") Long id, @RequestBody AbsenceReasonDto absenceReasonDto) {
        return ResponseEntity.ok(absenceReasonService.modifyAbsenceReasonById(id, absenceReasonDto));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsenceReasonById(@PathVariable("id") Long id) {
        absenceReasonService.removeAbsenceReasonById(id);

        return ResponseEntity.ok().build();
    }
}