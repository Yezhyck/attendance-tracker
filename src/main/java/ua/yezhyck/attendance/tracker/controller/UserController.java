package ua.yezhyck.attendance.tracker.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yezhyck.attendance.tracker.model.dto.UserDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.UserEditableDto;
import ua.yezhyck.attendance.tracker.service.impl.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserEditableDto userEditableDto) {
        return ResponseEntity.ok(userService.addUser(userEditableDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long id, @RequestBody UserEditableDto userEditableDto) {
        return ResponseEntity.ok(userService.modifyUserById(id, userEditableDto));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        userService.removeUserById(id);

        return ResponseEntity.ok().build();
    }
}