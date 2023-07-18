package ua.yezhyck.attendance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.dto.StudyClassEditableDto;
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

    @PutMapping("/{id}")
    public ResponseEntity<StudyClassDto> updateStudyClassById(@PathVariable("id") Long id, @RequestBody StudyClassEditableDto studyClassEditableDto) {
        return studyClassService.modifyStudyClassById(id, studyClassEditableDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/add-student/{id}")
    public ResponseEntity<StudyClassDto> addStudentToStudyClassById(@PathVariable("id") Long id, @RequestParam("studentId") Long studentId) {
        return studyClassService.addStudentToStudyClassById(id, studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/remove-student/{id}")
    public ResponseEntity<StudyClassDto> removeStudentFromStudyClassById(@PathVariable("id") Long id, @RequestParam("studentId") Long studentId) {
        return studyClassService.removeStudentFromStudyClassById(id, studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/add-lesson/{id}")
    public ResponseEntity<StudyClassDto> addLessonToStudyClassById(@PathVariable("id") Long id, @RequestParam("lessonId") Long lessonId) {
        return studyClassService.addLessonToStudyClassById(id, lessonId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/remove-lesson/{id}")
    public ResponseEntity<StudyClassDto> removeLessonFromStudyClassById(@PathVariable("id") Long id, @RequestParam("lessonId") Long lessonId) {
        return studyClassService.removeLessonToStudyClassById(id, lessonId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyClassById(@PathVariable("id") Long id) {
        if (!studyClassService.checkIfStudyClassExistsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studyClassService.removeStudyClassById(id);

        return ResponseEntity.ok().build();
    }
}