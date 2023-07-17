package ua.yezhyck.attendance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.LessonStudentDto;
import ua.yezhyck.attendance.tracker.dto.LessonStudentEditableDto;
import ua.yezhyck.attendance.tracker.service.LessonStudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/lesson-students")
public class LessonStudentController {
    private final LessonStudentServiceImpl lessonStudentService;

    @Autowired
    public LessonStudentController(LessonStudentServiceImpl lessonStudentService) {
        this.lessonStudentService = lessonStudentService;
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<LessonStudentDto> updateLessonStudentById(@PathVariable("id") Long id, @RequestBody LessonStudentEditableDto lessonStudentEditableDto) {
        return lessonStudentService.modifyLessonStudentById(id, lessonStudentEditableDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessonStudentById(@PathVariable("id") Long id) {
        if (!lessonStudentService.checkIfLessonStudentExistsById(id)) {
            return ResponseEntity.notFound().build();
        }

        lessonStudentService.removeLessonStudentById(id);

        return ResponseEntity.ok().build();
    }
}