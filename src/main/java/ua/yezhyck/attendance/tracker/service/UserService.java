package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.model.dto.UserDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.UserEditableDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchUserException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto addUser(UserEditableDto userEditableDto);

    Optional<UserDto> getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto modifyUserById(Long id, UserEditableDto userEditableDto) throws NoSuchUserException;

    void removeUserById(Long id) throws NoSuchUserException;
}