package ua.yezhyck.attendance.tracker.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
public class LessonDto {
    private Long id;
    private LocalDate date;

    @JsonProperty("lessonStudents")
    private List<LessonStudentDto> lessonStudentDtoList;
}