package ua.yezhyck.attendance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.LessonDto;
import ua.yezhyck.attendance.tracker.dto.LessonEditableDto;
import ua.yezhyck.attendance.tracker.service.impl.LessonServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonServiceImpl lessonService;

    @Autowired
    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonEditableDto lessonEditableDto) {
        return ResponseEntity.ok(lessonService.addLesson(lessonEditableDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> readLessonById(@PathVariable("id") Long id) {
        return lessonService.getLessonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LessonDto>> readAllLessons() {
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> updateLessonById(@PathVariable("id") Long id, @RequestBody LessonEditableDto lessonEditableDto) {
        return lessonService.modifyLessonById(id, lessonEditableDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/add-student/{id}")
    public ResponseEntity<LessonDto> addLessonStudentToLessonById(@PathVariable("id") Long id, @RequestParam("lessonStudentId") Long lessonStudentId) {
        return lessonService.addLessonStudentToLessonById(id, lessonStudentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/remove-student/{id}")
    public ResponseEntity<LessonDto> removeLessonStudentFromLessonClassById(@PathVariable("id") Long id, @RequestParam("lessonStudentId") Long lessonStudentId) {
        return lessonService.removeLessonStudentFromLessonById(id, lessonStudentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessonById(@PathVariable("id") Long id) {
        if (!lessonService.checkIfLessonExistsById(id)) {
            return ResponseEntity.notFound().build();
        }

        lessonService.removeLessonById(id);

        return ResponseEntity.ok().build();
    }
}