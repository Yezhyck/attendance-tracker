package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.domain.dto.UserDto;
import ua.yezhyck.attendance.tracker.domain.dto.editable.UserEditableDto;
import ua.yezhyck.attendance.tracker.domain.entity.Student;
import ua.yezhyck.attendance.tracker.exception.NoSuchUserException;
import ua.yezhyck.attendance.tracker.domain.mapper.UserMapper;
import ua.yezhyck.attendance.tracker.repository.StudentRepository;
import ua.yezhyck.attendance.tracker.repository.UserRepository;
import ua.yezhyck.attendance.tracker.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StudentRepository studentRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.studentRepository = studentRepository;
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
    public UserDto modifyUserById(Long id, UserEditableDto userEditableDto) throws NoSuchUserException {
        return userRepository.findById(id)
                .map(user -> {
                    user.setTelegramId(userEditableDto.getTelegramId());

                    return userMapper.mapToUserDto(userRepository.save(user));
                })
                .orElseThrow(() -> new NoSuchUserException(String.format("User does not exist with id=%d", id)));
    }

    @Override
    public void removeUserById(Long id) throws NoSuchUserException {
        if (!userRepository.existsById(id)) {
            throw new NoSuchUserException(String.format("User does not exist with id=%d", id));
        }

        List<Student> studentsForDelete = studentRepository.findWithOneStudyClassByUserId(id);

        userRepository.deleteById(id);
        studentRepository.deleteAll(studentsForDelete);
    }
}