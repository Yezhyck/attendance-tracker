package ua.yezhyck.attendance.tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ua.yezhyck.attendance.tracker.dto.StudyClassDto;
import ua.yezhyck.attendance.tracker.dto.editable.StudyClassEditableDto;
import ua.yezhyck.attendance.tracker.entity.StudyClass;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, LessonMapper.class})
public interface StudyClassMapper {
    StudyClassMapper INSTANCE = Mappers.getMapper(StudyClassMapper.class);

    @Mapping(source = "students", target = "studentDtoList")
    @Mapping(source = "lessons", target = "lessonDtoList")
    StudyClassDto mapToStudyClassDto(StudyClass studyClass);

    @Mapping(source = "studentDtoList", target = "students")
    @Mapping(source = "lessonDtoList", target = "lessons")
    StudyClass mapToStudyClass(StudyClassDto studyClassDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    StudyClass mapToStudyClass(StudyClassEditableDto studyClassEditableDto);

    List<StudyClassDto> mapAllToStudyClassDtoList(List<StudyClass> studyClasses);

    List<StudyClass> mapAllToStudyClasses(List<StudyClassDto> studyClassDtoList);
}