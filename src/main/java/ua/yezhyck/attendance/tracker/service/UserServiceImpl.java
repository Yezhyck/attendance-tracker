package ua.yezhyck.attendance.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.UserDto;
import ua.yezhyck.attendance.tracker.dto.UserEditableDto;
import ua.yezhyck.attendance.tracker.mapper.UserMapper;
import ua.yezhyck.attendance.tracker.repository.StudyClassRepository;
import ua.yezhyck.attendance.tracker.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StudyClassRepository studyClassRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           StudyClassRepository studyClassRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.studyClassRepository = studyClassRepository;
    }

    @Override
    public UserDto addUser(UserEditableDto userEditableDto) {
        return userMapper.mapToUserDto(userRepository.save(userMapper.mapToUser(userEditableDto)));
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::mapToUserDto);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.mapAllToUserDtoList(userRepository.findAll());
    }

    @Override
    public Optional<UserDto> modifyUserById(Long id, UserEditableDto userEditableDto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setTelegramId(userEditableDto.getTelegramId());

                    return userMapper.mapToUserDto(userRepository.save(user));
                });
    }

    @Override
    public Optional<UserDto> addStudyClassToUserById(Long id, Long studyClassId) {
        return userRepository.findById(id)
                .map(user -> {
                    studyClassRepository.findById(studyClassId)
                            .ifPresent(studyClass -> user.getStudyClasses().add(studyClass));

                    return userMapper.mapToUserDto(userRepository.save(user));
                });
    }

    @Override
    public Optional<UserDto> removeStudyClassFromUserById(Long id, Long studyClassId) {
        return userRepository.findById(id)
                .map(user -> {
                    studyClassRepository.findById(studyClassId)
                            .ifPresent(studyClass -> user.getStudyClasses().remove(studyClass));

                    return userMapper.mapToUserDto(userRepository.save(user));
                });
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkIfUserExistsById(Long id) {
        return userRepository.existsById(id);
    }
}