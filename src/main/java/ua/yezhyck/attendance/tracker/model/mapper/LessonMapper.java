package ua.yezhyck.attendance.tracker.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.yezhyck.attendance.tracker.model.dto.LessonDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.LessonEditableDto;
import ua.yezhyck.attendance.tracker.model.entity.Lesson;

import java.util.List;

@Mapper(componentModel = "spring", uses = LessonStudentMapper.class)
public interface LessonMapper {

    @Mapping(source = "lessonStudents", target = "lessonStudentDtoList")
    LessonDto mapToLessonDto(Lesson lesson);

    @Mapping(source = "lessonStudentDtoList", target = "lessonStudents")
    Lesson mapToLesson(LessonDto lessonDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studyClass", ignore = true)
    @Mapping(target = "lessonStudents", ignore = true)
    Lesson mapToLesson(LessonEditableDto lessonEditableDto);

    List<LessonDto> mapAllToLessonDtoList(List<Lesson> lessons);
}