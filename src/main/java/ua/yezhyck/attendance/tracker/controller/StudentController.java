package ua.yezhyck.attendance.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.dto.StudentDto;
import ua.yezhyck.attendance.tracker.service.StudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.addStudent(studentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> readStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> readAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudentById(@PathVariable("id") Long id, @RequestBody StudentDto studentDto) {
        return studentService.modifyStudentById(id, studentDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable("id") Long id) {
        if (!studentService.checkIfStudentExistsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentService.removeStudentById(id);

        return ResponseEntity.ok().build();
    }
}