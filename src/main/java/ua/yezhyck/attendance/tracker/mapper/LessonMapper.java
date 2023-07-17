package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.yezhyck.attendance.tracker.dto.LessonDto;
import ua.yezhyck.attendance.tracker.dto.LessonEditableDto;
import ua.yezhyck.attendance.tracker.entity.Lesson;

import java.util.List;

@Mapper(componentModel = "spring", uses = LessonStudentMapper.class)
public interface LessonMapper {
    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    @Mapping(source = "lessonStudents", target = "lessonStudentDtoList")
    LessonDto mapToLessonDto(Lesson lesson);

    @Mapping(source = "lessonStudentDtoList", target = "lessonStudents")
    Lesson mapToLesson(LessonDto lessonDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studyClass", ignore = true)
    Lesson mapToLesson(LessonEditableDto lessonEditableDto);

    List<LessonDto> mapAllToLessonDtoList(List<Lesson> lessons);

    List<Lesson> mapAllToLessons(List<LessonDto> lessonDtoList);

    @Mapping(source = "studyClass.id", target = "studyClassId")
    LessonEditableDto toLessonEditableDto(Lesson entity);
}