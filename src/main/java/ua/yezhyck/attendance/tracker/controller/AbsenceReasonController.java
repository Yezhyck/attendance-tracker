package ua.yezhyck.attendance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.AbsenceReasonDto;
import ua.yezhyck.attendance.tracker.service.AbsenceReasonServiceImpl;

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
    public @ResponseBody ResponseEntity<AbsenceReasonDto> createAbsenceReason(@RequestBody AbsenceReasonDto absenceReasonDto) {
        return ResponseEntity.ok(absenceReasonService.addAbsenceReason(absenceReasonDto));
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<AbsenceReasonDto> readAbsenceReasonById(@PathVariable("id") Long id) {
        return absenceReasonService.getAbsenceReasonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<AbsenceReasonDto>> readAllAbsenceReasons() {
        return ResponseEntity.ok(absenceReasonService.getAllAbsenceReasons());
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<AbsenceReasonDto> updateAbsenceReasonById(@PathVariable("id") Long id, @RequestBody AbsenceReasonDto absenceReasonDto) {
        return absenceReasonService.modifyAbsenceReasonById(id, absenceReasonDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<Void> deleteAbsenceReasonById(@PathVariable("id") Long id) {
        if (!absenceReasonService.checkIfAbsenceReasonExistsById(id)) {
            return ResponseEntity.notFound().build();
        }

        absenceReasonService.removeAbsenceReasonById(id);

        return ResponseEntity.ok().build();
    }
}