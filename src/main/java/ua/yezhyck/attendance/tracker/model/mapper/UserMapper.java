package ua.yezhyck.attendance.tracker.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.yezhyck.attendance.tracker.model.dto.UserDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.UserEditableDto;
import ua.yezhyck.attendance.tracker.model.entity.User;

import java.util.List;

@Mapper(componentModel = "spring", uses = StudyClassMapper.class)
public interface UserMapper {

    @Mapping(source = "studyClasses", target = "studyClassDtoList")
    UserDto mapToUserDto(User user);

    @Mapping(source = "studyClassDtoList", target = "studyClasses")
    User mapToUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studyClasses", ignore = true)
    User mapToUser(UserEditableDto userEditableDto);

    List<UserDto> mapAllToUserDtoList(List<User> users);
}