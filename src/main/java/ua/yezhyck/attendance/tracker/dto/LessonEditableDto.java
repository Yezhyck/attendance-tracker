package ua.yezhyck.attendance.tracker.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LessonEditableDto {
    private LocalDate date;
    private Long studyClassId;
}
