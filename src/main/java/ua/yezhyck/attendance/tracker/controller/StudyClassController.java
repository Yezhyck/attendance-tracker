package ua.yezhyck.attendance.tracker.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.model.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.StudyClassEditableDto;
import ua.yezhyck.attendance.tracker.service.impl.StudyClassServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/study-classes")
public class StudyClassController {
    private final StudyClassServiceImpl studyClassService;

    @Autowired
    public StudyClassController(StudyClassServiceImpl studyClassService) {
        this.studyClassService = studyClassService;
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<StudyClassDto> createStudyClass(@RequestBody StudyClassEditableDto studyClassEditableDto) {
        return ResponseEntity.ok(studyClassService.addStudyClass(studyClassEditableDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyClassDto> readStudyClassById(@PathVariable("id") Long id) {
        return studyClassService.getStudyClassById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudyClassDto>> readAllStudyClasses() {
        return ResponseEntity.ok(studyClassService.getAllStudyClasses());
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<StudyClassDto> updateStudyClassById(@PathVariable("id") Long id, @RequestBody StudyClassEditableDto studyClassEditableDto) {
        return ResponseEntity.ok(studyClassService.modifyStudyClassById(id, studyClassEditableDto));
    }

    @SneakyThrows
    @PutMapping("/add-student/{id}")
    public ResponseEntity<StudyClassDto> addStudentToStudyClassById(@PathVariable("id") Long id, @RequestParam("studentId") Long studentId) {
        return ResponseEntity.ok(studyClassService.addStudentToStudyClassById(id, studentId));
    }

    @SneakyThrows
    @PutMapping("/remove-student/{id}")
    public ResponseEntity<StudyClassDto> removeStudentFromStudyClassById(@PathVariable("id") Long id, @RequestParam("studentId") Long studentId) {
        return ResponseEntity.ok(studyClassService.removeStudentFromStudyClassById(id, studentId));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyClassById(@PathVariable("id") Long id) {
        studyClassService.removeStudyClassById(id);

        return ResponseEntity.ok().build();
    }
}