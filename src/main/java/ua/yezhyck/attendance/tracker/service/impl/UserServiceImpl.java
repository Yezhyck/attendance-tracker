package ua.yezhyck.attendance.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yezhyck.attendance.tracker.dto.UserDto;
import ua.yezhyck.attendance.tracker.dto.editable.UserEditableDto;
import ua.yezhyck.attendance.tracker.exception.NoSuchUserException;
import ua.yezhyck.attendance.tracker.mapper.UserMapper;
import ua.yezhyck.attendance.tracker.repository.UserRepository;
import ua.yezhyck.attendance.tracker.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkIfUserExistsById(Long id) {
        return userRepository.existsById(id);
    }
}