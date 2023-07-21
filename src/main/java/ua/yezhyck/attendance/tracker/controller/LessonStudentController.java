package ua.yezhyck.attendance.tracker.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.dto.editable.LessonStudentEditableDto;
import ua.yezhyck.attendance.tracker.service.impl.LessonStudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/lesson-students")
public class LessonStudentController {
    private final LessonStudentServiceImpl lessonStudentService;

    @Autowired
    public LessonStudentController(LessonStudentServiceImpl lessonStudentService) {
        this.lessonStudentService = lessonStudentService;
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<LessonStudentDto> createLessonStudent(@RequestBody LessonStudentEditableDto lessonStudentEditableDto) {
        return ResponseEntity.ok(lessonStudentService.addLessonStudent(lessonStudentEditableDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonStudentDto> readLessonStudentById(@PathVariable("id") Long id) {
        return lessonStudentService.getLessonStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LessonStudentDto>> readAllLessonStudents() {
        return ResponseEntity.ok(lessonStudentService.getAllLessonStudents());
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<LessonStudentDto> updateLessonStudentById(@PathVariable("id") Long id, @RequestBody LessonStudentEditableDto lessonStudentEditableDto) {
        return ResponseEntity.ok(lessonStudentService.modifyLessonStudentById(id, lessonStudentEditableDto));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessonStudentById(@PathVariable("id") Long id) {
        lessonStudentService.removeLessonStudentById(id);

        return ResponseEntity.ok().build();
    }
}