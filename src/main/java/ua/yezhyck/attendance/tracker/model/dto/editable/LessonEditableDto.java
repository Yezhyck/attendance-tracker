package ua.yezhyck.attendance.tracker.model.dto.editable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LessonEditableDto {
    private Long studyClassId;
    private LocalDate date;
}