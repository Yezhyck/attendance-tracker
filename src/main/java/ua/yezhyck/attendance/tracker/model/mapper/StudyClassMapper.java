package ua.yezhyck.attendance.tracker.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.yezhyck.attendance.tracker.model.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.model.dto.editable.StudyClassEditableDto;
import ua.yezhyck.attendance.tracker.model.entity.StudyClass;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, LessonMapper.class})
public interface StudyClassMapper {

    @Mapping(source = "students", target = "studentDtoList")
    @Mapping(source = "lessons", target = "lessonDtoList")
    StudyClassDto mapToStudyClassDto(StudyClass studyClass);

    @Mapping(source = "studentDtoList", target = "students")
    @Mapping(source = "lessonDtoList", target = "lessons")
    StudyClass mapToStudyClass(StudyClassDto studyClassDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    StudyClass mapToStudyClass(StudyClassEditableDto studyClassEditableDto);

    List<StudyClassDto> mapAllToStudyClassDtoList(List<StudyClass> studyClasses);
}