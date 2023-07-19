package ua.yezhyck.attendance.tracker.service;

import ua.yezhyck.attendance.tracker.dto.UserDto;
import ua.yezhyck.attendance.tracker.dto.editable.UserEditableDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto addUser(UserEditableDto userEditableDto);

    Optional<UserDto> getUserById(Long id);

    List<UserDto> getAllUsers();

    Optional<UserDto> modifyUserById(Long id, UserEditableDto userEditableDto);

    Optional<UserDto> addStudyClassToUserById(Long id, Long studyClassId);

    Optional<UserDto> removeStudyClassFromUserById(Long id, Long studyClassId);

    void removeUserById(Long id);

    boolean checkIfUserExistsById(Long id);
}