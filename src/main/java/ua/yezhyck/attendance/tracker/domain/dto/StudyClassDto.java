package ua.yezhyck.attendance.tracker.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StudyClassDto {
    private Long id;
    private String name;

    @JsonProperty("students")
    private List<StudentDto> studentDtoList;

    @JsonProperty("lessons")
    private List<LessonDto> lessonDtoList;
}